package visrec.impl.deepnetts.examples;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import visrec.classifier.AbstractImageClassifier;
import visrec.classifier.ClassificationResults;
import visrec.impl.deepnetts.DeepNettsImageClassifier;
import visrec.impl.deepnetts.DeepNettsImageClassifier;


/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class Cifar10Demo {

    public static void main(String[] args) throws IOException {
                  
        // BUILD IMAGE CLASSIFIER USING VIS REC API
        
        Properties prop = new Properties();         
        // provide data set properties
        prop.setProperty("visrec.imageWidth", "32");   
        prop.setProperty("visrec.imageHeight", "32");        
        prop.setProperty("visrec.labelsFile", "/home/zoran/datasets/cifar10/train/labels.txt");   // list of labels in txt file
        prop.setProperty("visrec.trainingFile", "/home/zoran/datasets/cifar10/train/train.txt");  // list of training images in txt file
        prop.setProperty("visrec.testFile", "/home/zoran/datasets/cifar10/test/test.txt");        // list of test images in txt file
        
        // provide model properties -  how to specify network architecture ??? could the model be injected?
        //prop.setProperty("networkArch", "architecture.json"); // how to do it programaticly? for different models?
                       
        // training settings
        prop.setProperty("visrec.sgd.maxError", "0.03");
        prop.setProperty("visrec.sgd.learningRate", "0.01");        
        prop.setProperty("modelFile", "cifar10.dnet");  // save trained model in file at the end (model has to provide this feature)        
        
        AbstractImageClassifier imageClassifier = new DeepNettsImageClassifier(); // maybe also attach some listener to be notified when model building is complete?
        imageClassifier.build(prop);    // the build should provide standard workflow that can be easily customized (preprocessing etc.)
        
        System.out.println("Done building image classifier.");
                         
        // TEST VISREC IMAGE CLASSIFIER
        // TODO ...
                
        // USE VISREC IMAGE CLASSIFIER        
        System.out.println("Classifiying images ...");
        ClassificationResults results = imageClassifier.classify(new File("/home/zoran/datasets/Cifar10/train/airplane/someTestImage.png"));        
        System.out.println(results);
                        
        System.out.println("Done.");              
    }        
}
