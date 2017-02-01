package visrec.impl.dl4j;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.split.FileSplit;
import org.datavec.api.split.InputSplit;
import org.datavec.image.loader.ImageLoader;
import org.datavec.image.loader.NativeImageLoader;
import org.datavec.image.recordreader.ImageRecordReader;
import org.datavec.image.transform.ImageTransform;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.datasets.iterator.MultipleEpochsIterator;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import visrec.classifier.ImageClassifier;
import visrec.util.BufferedImageFactory;
import visrec.util.ImageRecognitionResults;
import visrec.util.ImageRecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class Dl4jImageClassifier extends ImageClassifier<BufferedImage, MultiLayerNetwork> {
     MultiLayerNetwork neuralNet;

    public Dl4jImageClassifier(String fileName) throws IOException {
        neuralNet = ModelSerializer.restoreMultiLayerNetwork(fileName);
        setImageFactory(new BufferedImageFactory());
    }
    
    public Dl4jImageClassifier(File file) throws IOException {
        neuralNet = ModelSerializer.restoreMultiLayerNetwork(file);
        setImageFactory(new BufferedImageFactory());
    }    

    public Dl4jImageClassifier(MultiLayerNetwork neuralNet) {
        this.neuralNet = neuralNet;
        setImageFactory(new BufferedImageFactory());
    }
        

    @Override
    public ImageRecognitionResults classify(BufferedImage sample) {
        ImageLoader imageLoader = new ImageLoader();                
        INDArray input =  imageLoader.asRowVector(sample);     
        
        // feed netwrk with image       
        INDArray output = neuralNet.output(input);
        // how to get labels from network?
        
        // get output
        // get label
        ImageRecognitionResults results = new ImageRecognitionResults();
        // transform here binary network outpit to ImageRecognitionResult
        for(int i = 0; i< output.getRow(0).length(); i++ ) {
            double score = output.getFloat(0, i);
            if (score > 0.5) {
                ImageRecognitionResult r = new ImageRecognitionResult( (i+1)+" ", score ); // get label here
                results.add(r);
            }
        }
        
        return results;
    }

    // maybe provide builder class as a parameter
    
    // what information is required for building classifier and how to provide them?
    // maybe put it in the Classifier specific class?
    // we would need machine learning pipeline
    
    @Override
    public void buildClassifier(Map data) {
        // crewate ImageRecordReader from given map
        
        // image diemnsions and channels
        int height = 100;
        int width = 100;
        int channels = 3;     
        
        String imagesPath = "imageSetPath";
        
        // this should be used automaticaly
        int nCores = 2;        
        //nCores = Runtime.getRuntime().availableProcessors() - 1;
        int seed=123;
        
        // training settings
        int epochs = 50; // should we limit this? it should be trained until it learns images ...
              int batchSize = 20;
        double splitTrainTest = 0.8; // ratio of training / test set
     
        
        int numLabels = 4; // we should be able to get this from training data
    
        Random rng = new Random(seed);
        
        // provide path to directory with images

        ParentPathLabelGenerator labelMaker = new ParentPathLabelGenerator();
        
        File mainPath = new File(imagesPath);
        FileSplit fileSplit = new FileSplit(mainPath, NativeImageLoader.ALLOWED_FORMATS, rng);        
        
        /**
         * Data Setup -> train test split
         *  - inputSplit = define train and test split
         **/
        InputSplit[] inputSplit = fileSplit.sample(pathFilter, numExamples * (1 + splitTrainTest), numExamples * (1 - splitTrainTest));
        InputSplit trainData = inputSplit[0];
        InputSplit testData = inputSplit[1];        
        
        /**
         * Data Setup -> define how to load data into net:
         *  - recordReader = the reader that loads and converts image data pass in inputSplit to initialize
         *  - dataIter = a generator that only loads one batch at a time into memory to save memory
         *  - trainIter = uses MultipleEpochsIterator to ensure model runs through the data for all epochs
         **/        
        ImageRecordReader recordReader = new ImageRecordReader(height, width, channels, labelMaker);
        DataSetIterator dataIter;
        MultipleEpochsIterator trainIter;


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
        }        
        
    }




}
