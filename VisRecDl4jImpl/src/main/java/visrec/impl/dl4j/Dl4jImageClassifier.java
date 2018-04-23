package visrec.impl.dl4j;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.visrec.AbstractImageClassifier;
import javax.visrec.ml.classification.Classifier;
import javax.visrec.util.BufferedImageFactory;
import org.datavec.api.io.filters.BalancedPathFilter;
import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.split.FileSplit;
import org.datavec.api.split.InputSplit;
import org.datavec.image.loader.ImageLoader;
import org.datavec.image.loader.NativeImageLoader;
import org.datavec.image.recordreader.ImageRecordReader;
import org.datavec.image.transform.FlipImageTransform;
import org.datavec.image.transform.ImageTransform;
import org.datavec.image.transform.WarpImageTransform;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.datasets.iterator.MultipleEpochsIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.GradientNormalization;
import org.deeplearning4j.nn.conf.LearningRatePolicy;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.distribution.Distribution;
import org.deeplearning4j.nn.conf.distribution.GaussianDistribution;
import org.deeplearning4j.nn.conf.distribution.NormalDistribution;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.LocalResponseNormalization;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.lossfunctions.LossFunctions;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class Dl4jImageClassifier extends AbstractImageClassifier<BufferedImage, MultiLayerNetwork> {

    public Dl4jImageClassifier() {
        super(BufferedImage.class);
    }

    public Dl4jImageClassifier(String fileName) throws IOException {
        this();
        MultiLayerNetwork neuralNet = ModelSerializer.restoreMultiLayerNetwork(fileName);
        setModel(neuralNet);
    }

    public Dl4jImageClassifier(File file) throws IOException {
        this();
        MultiLayerNetwork neuralNet = ModelSerializer.restoreMultiLayerNetwork(file);
        setModel(neuralNet);
    }

    public Dl4jImageClassifier(MultiLayerNetwork neuralNet) {
        this();
        setModel(neuralNet);
    }

    @Override
    public Map<String, Float> classify(BufferedImage sample) {
        MultiLayerNetwork neuralNet = getModel();

        ImageLoader imageLoader = new ImageLoader();
        INDArray input = imageLoader.asRowVector(sample);

        // feed netwrk with image
        INDArray output = neuralNet.output(input);
        // how to get labels from network?

        // get output
        // get label
        Map<String, Float> results = new HashMap<>();
        // transform here binary network outpit to DnRecognitionResult
        for (int i = 0; i < output.getRow(0).length(); i++) {
            float score = output.getFloat(0, i);
            if (score > 0.5) {
                results.put((i + 1) + " ", score);
            }
        }

        return results;
    }

    // maybe provide builder class as a parameter
    // what information is required for building classifier and how to provide them?
    // maybe put it in the Classifier specific class?
    // we would need machine learning pipeline
    // use Properties
    public Classifier build(Properties prop) {
        try {

            // image diemnsions and channels
//        int width = 100;
//        int height = 100;
//        int channels = 3;
            String imagesPath = (String) prop.getProperty("imagesPath");
            int width = Integer.parseInt(prop.getProperty("imageWidth"));
            int height = Integer.parseInt(prop.getProperty("imageHeight"));
            int channels = 3;

// this should be used automaticaly (can be set or used deafault)
            int nCores = 2;
//nCores = Runtime.getRuntime().availableProcessors() - 1;
            int seed = 123;   // this is only needed for testing

// training settings
            int iterations = 1;
            int epochs = 50; // should we limit this? it should be trained until it learns images ...
            int batchSize = 20;
            double splitTrainTest = 0.8; // ratio of training / test set - use this as default
            int numExamples = 80;

            int numLabels = 4; // we should be able to get this from training data

            Random rng = new Random(seed);

// provide path to directory with images
            ParentPathLabelGenerator labelMaker = new ParentPathLabelGenerator();

            File mainPath = new File(imagesPath);
            FileSplit fileSplit = new FileSplit(mainPath, NativeImageLoader.ALLOWED_FORMATS, rng);
            BalancedPathFilter pathFilter = new BalancedPathFilter(rng, labelMaker, numExamples, numLabels, batchSize);

            /**
             * Data Setup -> train test split - inputSplit = define train and
             * test split
             *
             */
            InputSplit[] inputSplit = fileSplit.sample(pathFilter, numExamples * (1 + splitTrainTest), numExamples * (1 - splitTrainTest));
            InputSplit trainData = inputSplit[0];
            InputSplit testData = inputSplit[1];

            DataNormalization scaler = new ImagePreProcessingScaler(0, 1);

            ImageTransform flipTransform1 = new FlipImageTransform(rng);
            ImageTransform flipTransform2 = new FlipImageTransform(new Random(123));
            ImageTransform warpTransform = new WarpImageTransform(rng, 42);
//        ImageTransform colorTransform = new ColorConversionTransform(new Random(seed), COLOR_BGR2YCrCb);
            List<ImageTransform> transforms = Arrays.asList(new ImageTransform[]{flipTransform1, warpTransform, flipTransform2});

            /**
             * Data Setup -> define how to load data into net: - recordReader =
             * the reader that loads and converts image data pass in inputSplit
             * to initialize - dataIter = a generator that only loads one batch
             * at a time into memory to save memory - trainIter = uses
             * MultipleEpochsIterator to ensure model runs through the data for
             * all epochs
             *
             */
            ImageRecordReader recordReader = new ImageRecordReader(height, width, channels, labelMaker);
            DataSetIterator dataIter;
            MultipleEpochsIterator trainIter;

            MultiLayerNetwork neuralNet = alexnetModel(seed, iterations, channels, numLabels, width, height);

//  log.info("Train model....");
// Train without transformations
            recordReader.initialize(trainData, null);
            dataIter = new RecordReaderDataSetIterator(recordReader, batchSize, 1, numLabels);
            scaler.fit(dataIter);
            dataIter.setPreProcessor(scaler);
            trainIter = new MultipleEpochsIterator(epochs, dataIter, nCores);
            neuralNet.fit(trainIter);

// Train with transformations
            for (ImageTransform transform : transforms) {
                //    System.out.print("\nTraining on transformation: " + transform.getClass().toString() + "\n\n");
                recordReader.initialize(trainData, transform);
                dataIter = new RecordReaderDataSetIterator(recordReader, batchSize, 1, numLabels);
                scaler.fit(dataIter);
                dataIter.setPreProcessor(scaler);
                trainIter = new MultipleEpochsIterator(epochs, dataIter, nCores);
                neuralNet.fit(trainIter);

                setModel(neuralNet);
            }
            return this;
        } catch (IOException ex) {
            Logger.getLogger(Dl4jImageClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this;
    }

    public MultiLayerNetwork alexnetModel(int seed, int iterations, int channels, int numLabels, int width, int height) {
        /**
         * AlexNet model interpretation based on the original paper ImageNet
         * Classification with Deep Convolutional Neural Networks and the
         * imagenetExample code referenced.
         * http://papers.nips.cc/paper/4824-imagenet-classification-with-deep-convolutional-neural-networks.pdf
         *
         */

        double nonZeroBias = 1;
        double dropOut = 0.5;

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .weightInit(WeightInit.DISTRIBUTION)
                .dist(new NormalDistribution(0.0, 0.01))
                .activation(Activation.RELU)
                .updater(Updater.NESTEROVS)
                .iterations(iterations)
                .gradientNormalization(GradientNormalization.RenormalizeL2PerLayer) // normalize to prevent vanishing or exploding gradients
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .learningRate(1e-2)
                .biasLearningRate(1e-2 * 2)
                .learningRateDecayPolicy(LearningRatePolicy.Step)
                .lrPolicyDecayRate(0.1)
                .lrPolicySteps(100000)
                .regularization(true)
                .l2(5 * 1e-4)
                .momentum(0.9)
                .miniBatch(false)
                .list()
                .layer(0, convInit("cnn1", channels, 96, new int[]{11, 11}, new int[]{4, 4}, new int[]{3, 3}, 0))
                .layer(1, new LocalResponseNormalization.Builder().name("lrn1").build())
                .layer(2, maxPool("maxpool1", new int[]{3, 3}))
                .layer(3, conv5x5("cnn2", 256, new int[]{1, 1}, new int[]{2, 2}, nonZeroBias))
                .layer(4, new LocalResponseNormalization.Builder().name("lrn2").build())
                .layer(5, maxPool("maxpool2", new int[]{3, 3}))
                .layer(6, conv3x3("cnn3", 384, 0))
                .layer(7, conv3x3("cnn4", 384, nonZeroBias))
                .layer(8, conv3x3("cnn5", 256, nonZeroBias))
                .layer(9, maxPool("maxpool3", new int[]{3, 3}))
                .layer(10, fullyConnected("ffn1", 4096, nonZeroBias, dropOut, new GaussianDistribution(0, 0.005)))
                .layer(11, fullyConnected("ffn2", 4096, nonZeroBias, dropOut, new GaussianDistribution(0, 0.005)))
                .layer(12, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .name("output")
                        .nOut(numLabels)
                        .activation(Activation.SOFTMAX)
                        .build())
                .backprop(true)
                .pretrain(false)
                .setInputType(InputType.convolutional(height, width, channels))
                .build();

        return new MultiLayerNetwork(conf);

    }

    private ConvolutionLayer convInit(String name, int in, int out, int[] kernel, int[] stride, int[] pad, double bias) {
        return new ConvolutionLayer.Builder(kernel, stride, pad).name(name).nIn(in).nOut(out).biasInit(bias).build();
    }

    private ConvolutionLayer conv3x3(String name, int out, double bias) {
        return new ConvolutionLayer.Builder(new int[]{3, 3}, new int[]{1, 1}, new int[]{1, 1}).name(name).nOut(out).biasInit(bias).build();
    }

    private ConvolutionLayer conv5x5(String name, int out, int[] stride, int[] pad, double bias) {
        return new ConvolutionLayer.Builder(new int[]{5, 5}, stride, pad).name(name).nOut(out).biasInit(bias).build();
    }

    private SubsamplingLayer maxPool(String name, int[] kernel) {
        return new SubsamplingLayer.Builder(kernel, new int[]{2, 2}).name(name).build();
    }

    private DenseLayer fullyConnected(String name, int out, double bias, double dropOut, Distribution dist) {
        return new DenseLayer.Builder().name(name).nOut(out).biasInit(bias).dropOut(dropOut).dist(dist).build();
    }

}
