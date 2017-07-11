package visrec.impl.deepnetts;

import deepnetts.conv.ActivationFunctions;
import deepnetts.conv.ActivationType;
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
import visrec.classifier.AbstractImageClassifier;
import visrec.classifier.ClassificationResult;
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
    public List<ClassificationResult<String>> classify(BufferedImage sample) {
        List<ClassificationResult<String>>  results = new ArrayList<>();
        DeepNettsNetwork neuralNet = getModel();
                
        ExampleImage exImage = new ExampleImage(sample);
        neuralNet.setInput(exImage.getInputMatrix());
        neuralNet.forward();
        float[] outputs = neuralNet.getOutput(); // change to double[]

       float max = outputs[0];       
       int maxIdx = 0;
       for(int i=1; i<outputs.length; i++) {
           if (outputs[i] > max) {
               max = outputs[i];
               maxIdx = i;
           }
       }
             
       ClassificationResult result = new ClassificationResult(neuralNet.getOutputLabel(maxIdx), max);
       results.add(result);
       
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
    //    try {
            imageSet.loadImages(new File(trainingFile), 100); // napomena - putanje bi trebalo da budu relativne inace moraju da se regenerisu 
//        } catch (IOException | DeepNettsException ex) {
//            java.util.logging.Logger.getLogger(DeepNettsImageClassifier.class.getName()).log(Level.SEVERE, null, ex);
//        }
    
        int classCount = imageSet.getLabelsCount();
        
        LOGGER.info("Done!");             
        LOGGER.info("Creating neural network...");
        
        ConvolutionalNetwork neuralNet = new ConvolutionalNetwork.Builder()
                                        .inputLayer(imageWidth, imageHeight, 3) 
                                        .convolutionalLayer(5, 5, 3, ActivationType.TANH) 
                                        .poolingLayer(2, 2, 2) 
                                        .convolutionalLayer(5, 5, 6, ActivationType.TANH) 
                                        .poolingLayer(2, 2, 2)                 
                                        .fullyConnectedLayer(30, ActivationType.TANH)  // F6 this layer mus tbe connected to all neurons in previous layer!
                                        .outputLayer(classCount, SoftmaxOutputLayer.class) // softmax output // labelsCount
                                        .lossFunction(CrossEntropyLoss.class)
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
