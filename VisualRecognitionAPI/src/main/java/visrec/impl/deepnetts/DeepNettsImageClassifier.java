package visrec.impl.deepnetts;

import deepnetts.data.ExampleImage;
import deepnetts.data.ImageSet;
import deepnetts.net.ConvolutionalNetwork;
import deepnetts.net.NeuralNetwork;
import deepnetts.net.layers.ActivationType;
import deepnetts.net.layers.SoftmaxOutputLayer;
import deepnetts.net.loss.CrossEntropyLoss;
import deepnetts.net.train.BackpropagationTrainer;
import deepnetts.net.train.OptimizerType;
import deepnetts.util.DeepNettsException;
import deepnetts.util.FileIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import visrec.classifier.AbstractImageClassifier;
import visrec.classifier.ClassificationResult;
import visrec.classifier.ClassificationResults;
import visrec.classifier.Classifier;
import visrec.util.BufferedImageFactory;
import visrec.util.VisRec;

/**
 * TODO: Traffic sign recognition
 *       healhcare - skin, 
 *                  radiology caner for bone - MRI 
 *          vegetarian or non vegeterian food, transportation boxes
 *        simple implementation and ditributed implemantayion using th esame api as hazelcast          
 *          trab
 *          what is teh biggest challange for javadeveloper starting tio use it
 *          apache or gpl wit classpath , allow dynamic linkoing
 *          
 *          separate training from using. (resource usage)
 *          3d stereo vision - using conv networks
 *          use configuration JSR  apache 382
 *          iso standardize containers
 *          thermal imaging
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class DeepNettsImageClassifier extends AbstractImageClassifier<BufferedImage, ConvolutionalNetwork>{
    
    private int imageWidth, imageHeight;

    public static final Logger LOGGER = Logger.getLogger(DeepNettsImageClassifier.class.getName());

    public DeepNettsImageClassifier() {
        setImageFactory(new BufferedImageFactory()); // inject image factory
    }
       
    
    @Override
    public ClassificationResults classify(BufferedImage sample) {
        ClassificationResults<ClassificationResult> results = new ClassificationResults();                
        NeuralNetwork neuralNet = getModel();
                
        ExampleImage exImage = new ExampleImage(sample);
        neuralNet.setInput(exImage.getInput());
        neuralNet.forward();
        
       float[] outputs = neuralNet.getOutput();

       for(int i=1; i<outputs.length; i++) {
           results.add(new ClassificationResult(neuralNet.getOutputLabel(i), outputs[i]));
       }

       return results;                        
    }


    public Classifier build(Properties prop) {
        
        imageWidth = Integer.parseInt(prop.getProperty(VisRec.IMAGE_WIDTH));
        imageHeight = Integer.parseInt(prop.getProperty(VisRec.IMAGE_HEIGHT));
        String labelsFile = prop.getProperty(VisRec.LABELS_FILE);
        String trainingFile = prop.getProperty(VisRec.TRAINING_FILE);
        float maxError = Float.parseFloat(prop.getProperty(VisRec.SGD_MAX_ERROR));
        float learningRate = Float.parseFloat(prop.getProperty(VisRec.SGD_LEARNING_RATE));
        
        String modelFile = prop.getProperty("visrec.model.saveToFile");
                
        ImageSet imageSet = new ImageSet(imageWidth, imageHeight);        
        LOGGER.info("Loading images...");
        
        imageSet.loadLabels(new File(labelsFile));
        try {
            imageSet.loadImages(new File(trainingFile), true); // paths in training file should be relative
            imageSet.invert();
            imageSet.zeroMean();
            imageSet.shuffle();            
        } catch (IOException | DeepNettsException ex) {
            java.util.logging.Logger.getLogger(DeepNettsImageClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        int classCount = imageSet.getLabelsCount();
        
        LOGGER.info("Done!");             
        LOGGER.info("Creating neural network...");
        
        
//        String modelJsonFile = prop.getProperty("visrec.model.deepnetts");
//        ConvolutionalNetwork neuralNet = null;
//        try {
//            neuralNet = FileIO.createFromJson(new File(modelJsonFile));
//            //neuralNet.setOutputLabels(imageSet.getLabels());
//        } catch (IOException ex) {
//            Logger.getLogger(DeepNettsImageClassifier.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        ConvolutionalNetwork neuralNet = new ConvolutionalNetwork.Builder()
                                        .addInputLayer(imageWidth, imageHeight, 3) 
                                        .addConvolutionalLayer(5, 5, 3, ActivationType.RELU)
                                        .addMaxPoolingLayer(2, 2, 2)        
                                        .addConvolutionalLayer(3, 3, 6, ActivationType.RELU) 
                                        .addMaxPoolingLayer(2, 2, 2)       
                                        .addFullyConnectedLayer(30, ActivationType.RELU)
                                        .addFullyConnectedLayer(20, ActivationType.RELU)
                                        .addOutputLayer(classCount, SoftmaxOutputLayer.class)
                                        .lossFunction(CrossEntropyLoss.class)
                                        .randomSeed(123)       
                                        .build();  

        LOGGER.info("Done!");       
        LOGGER.info("Training neural network"); 
        
        neuralNet.setOutputLabels(imageSet.getLabels());
               
        // create a set of convolutional networks and do training, crossvalidation and performance evaluation     
        BackpropagationTrainer trainer = new BackpropagationTrainer(neuralNet);
        trainer.setLearningRate(learningRate)
                .setMomentum(0.7f)
                .setMaxError(maxError)
                .setBatchMode(false)
                .setOptimizer(OptimizerType.MOMENTUM);
        trainer.train(imageSet);         
        
        setModel(neuralNet);
          
        try {
            FileIO.writeToFile(neuralNet, modelFile);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(DeepNettsImageClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this;
        
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }
    
}