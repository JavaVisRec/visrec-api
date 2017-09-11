package visrec.impl.deepnetts;

import deepnetts.core.ActivationFunctions;
import deepnetts.core.ActivationType;
import deepnetts.core.BackpropagationTrainer;
import deepnetts.net.conv.ConvolutionalNetwork;
import deepnetts.core.DeepNettsException;
import deepnetts.core.DeepNettsNetwork;
import deepnetts.core.OptimizerType;
import deepnetts.core.loss.CrossEntropyLoss;
import deepnetts.data.ExampleImage;
import deepnetts.data.ImageSet;
import deepnetts.io.FileIO;
import deepnetts.core.layers.SoftmaxOutputLayer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import visrec.classifier.AbstractImageClassifier;
import visrec.classifier.ClassificationResult;
import visrec.classifier.ClassificationResults;
import visrec.util.BufferedImageFactory;
import visrec.util.VisRec;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class DeepNettsImageClassifier extends AbstractImageClassifier<BufferedImage, DeepNettsNetwork>{
    
    private int imageWidth, imageHeight;

    public static final Logger LOGGER = Logger.getLogger(DeepNettsImageClassifier.class.getName());

    public DeepNettsImageClassifier() {
        setImageFactory(new BufferedImageFactory()); // inject image factory
    }
       
    
    @Override
    public ClassificationResults classify(BufferedImage sample) {
        ClassificationResults results = new ClassificationResults();                
        DeepNettsNetwork neuralNet = getModel();
                
        ExampleImage exImage = new ExampleImage(sample);
        neuralNet.setInput(exImage.getInputMatrix());
        neuralNet.forward();
        
       float[] outputs = neuralNet.getOutput();

       for(int i=1; i<outputs.length; i++) {
           results.add(neuralNet.getOutputLabel(i), outputs[i]);
       }

       return results;                        
    }

    @Override
    public void build(Properties prop) {
        
        imageWidth = Integer.parseInt(prop.getProperty(VisRec.IMAGE_WIDTH));
        imageHeight = Integer.parseInt(prop.getProperty(VisRec.IMAGE_HEIGHT));
        String labelsFile = prop.getProperty(VisRec.LABELS_FILE);
        String trainingFile = prop.getProperty(VisRec.TRAINING_FILE);
        float maxError = Float.parseFloat(prop.getProperty(VisRec.SGD_MAX_ERROR));
        float learningRate = Float.parseFloat(prop.getProperty(VisRec.SGD_LEARNING_RATE));
        
        String modelFile = prop.getProperty("modelFile");
                
        ImageSet imageSet = new ImageSet(imageWidth, imageHeight);        
        LOGGER.info("Loading images...");
        
        imageSet.loadLabels(new File(labelsFile));
        try {
            imageSet.loadImages(new File(trainingFile)); // napomena - putanje bi trebalo da budu relativne inace moraju da se regenerisu 
            imageSet.invert();
            imageSet.zeroMean();
            imageSet.shuffle();            
        } catch (IOException | DeepNettsException ex) {
            java.util.logging.Logger.getLogger(DeepNettsImageClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        int classCount = imageSet.getLabelsCount();
        
        LOGGER.info("Done!");             
        LOGGER.info("Creating neural network...");
        
//        ConvolutionalNetwork neuralNet = new ConvolutionalNetwork.Builder()
//                                        .inputLayer(imageWidth, imageHeight, 3) 
//                                        .convolutionalLayer(5, 5, 6, ActivationType.TANH)
//                                        .maxPoolingLayer(2, 2, 2)  
//                                        .fullyConnectedLayer(80, ActivationType.TANH) 
//                                        .fullyConnectedLayer(40, ActivationType.TANH)     
//                                        .outputLayer(classCount, SoftmaxOutputLayer.class)
//                                        .lossFunction(CrossEntropyLoss.class)                
//                                        .build();      

        ConvolutionalNetwork neuralNet = new ConvolutionalNetwork.Builder()
                                        .inputLayer(imageWidth, imageHeight, 3) 
                                        .convolutionalLayer(5, 5, 3, ActivationType.RELU)
                                        .maxPoolingLayer(2, 2, 2)        
                                        .convolutionalLayer(3, 3, 6, ActivationType.RELU) 
                                        .maxPoolingLayer(2, 2, 2)       
                                        .fullyConnectedLayer(30, ActivationType.RELU)
                                        .fullyConnectedLayer(20, ActivationType.RELU)
                                        .outputLayer(classCount, SoftmaxOutputLayer.class)
                                        .lossFunction(CrossEntropyLoss.class)
                                        .randomSeed(123)       
                                        .build();  

        LOGGER.info("Done!");       
        LOGGER.info("Training neural network"); 
        
        neuralNet.setOutputLabels(imageSet.getLabels());
        
        // create a set of convolutional networks and do training, crossvalidation and performance evaluation
//        BackpropagationTrainer trainer = new BackpropagationTrainer(neuralNet);
//        trainer.setLearningRate(learningRate);
//        trainer.setMaxError(maxError);
//        trainer.setMomentum(0.9f); 
//        trainer.setOptimizer(OptimizerType.MOMENTUM); 
//       // trainer.setBatchMode(false); // false by default
//        trainer.train(imageSet);   
        
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
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }
    
    

}
