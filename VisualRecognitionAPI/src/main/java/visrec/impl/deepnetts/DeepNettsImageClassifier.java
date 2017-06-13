package visrec.impl.deepnetts;

import deepnetts.conv.ActivationFunctions;
import deepnetts.conv.BackpropagationTrainer;
import deepnetts.conv.ConvolutionalNetwork;
import deepnetts.core.DeepNettsException;
import deepnetts.core.DeepNettsNetwork;
import deepnetts.core.loss.CrossEntropyLoss;
import deepnetts.data.ExampleImage;
import deepnetts.data.ImageSet;
import deepnetts.io.FileIO;
import deepnetts.layers.SoftmaxOutputLayer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import visrec.classifier.ImageClassifier;
import visrec.util.BufferedImageFactory;
import visrec.util.RecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class DeepNettsImageClassifier extends ImageClassifier<BufferedImage, DeepNettsNetwork>{
    
    private int imageWidth, imageHeight;

    public static final Logger LOGGER = Logger.getLogger(DeepNettsImageClassifier.class.getName());

    public DeepNettsImageClassifier() {
        setImageFactory(new BufferedImageFactory());
    }
    
    
    
    @Override
    public List<RecognitionResult> classify(BufferedImage sample) {
        List<RecognitionResult> results = new ArrayList<>();
        DeepNettsNetwork neuralNet = getModel();
                
        ExampleImage exImage = new ExampleImage(sample);
        neuralNet.setInput(exImage.getInputMatrix());
        neuralNet.forward();
        double[] outputs = neuralNet.getOutput();

       double max = outputs[0];       
       int maxIdx = 0;
       for(int i=1; i<outputs.length; i++) {
           if (outputs[i] > max) {
               max = outputs[i];
               maxIdx = i;
           }
       }
             
       RecognitionResult result = new RecognitionResult(neuralNet.getOutputLabel(maxIdx), max);
       results.add(result);
       
       return results;                        
    }

    @Override
    public void build(Properties prop) {
        
        imageWidth = Integer.parseInt(prop.getProperty("imageWidth"));
        imageHeight = Integer.parseInt(prop.getProperty("imageHeight"));
        String labelsFile = prop.getProperty("labelsFile");
        String trainingFile = prop.getProperty("trainingFile");
        double maxError = Double.parseDouble(prop.getProperty("maxError"));
        double learningRate = Double.parseDouble(prop.getProperty("learningRate"));
        String modelFile = prop.getProperty("modelFile");
                
        ImageSet imageSet = new ImageSet(imageWidth, imageHeight);        
        LOGGER.info("Loading images...");
        
        imageSet.loadLabels(new File(labelsFile));
        try {
            imageSet.loadImages(new File(trainingFile)); // napomena - putanje bi trebalo da budu relativne inace moraju da se regenerisu 
        } catch (IOException | DeepNettsException ex) {
            java.util.logging.Logger.getLogger(DeepNettsImageClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        int classCount = imageSet.getLabelsCount();
        
        LOGGER.info("Done!");             
        LOGGER.info("Creating neural network...");
        
        ConvolutionalNetwork neuralNet = new ConvolutionalNetwork.Builder()
                                        .withInputLayer(imageWidth, imageHeight, 3) 
                                        .withConvolutionalLayer(5, 5, 3, ActivationFunctions.TANH) 
                                        .withPoolingLayer(2, 2, 2) 
                                        .withConvolutionalLayer(5, 5, 6, ActivationFunctions.TANH) 
                                        .withPoolingLayer(2, 2, 2)                 
                                        .withFullyConnectedLayer(30, ActivationFunctions.TANH)  // F6 this layer mus tbe connected to all neurons in previous layer!
                                        .withOutputLayer(classCount, SoftmaxOutputLayer.class) // softmax output // labelsCount
                                        .withLossFunction(CrossEntropyLoss.class)
                                        .build();        

        LOGGER.info("Done!");       
        LOGGER.info("Training neural network"); 
        
        neuralNet.setOutputLabels(imageSet.getLabels());
        
        // create a set of convolutional networks and do training, crossvalidation and performance evaluation
        BackpropagationTrainer trainer = new BackpropagationTrainer(neuralNet);
        trainer.setLearningRate(learningRate)
//               .setMomentum(0.01)
               .setMaxError(maxError);
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
