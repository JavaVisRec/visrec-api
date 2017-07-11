package visrec.examples;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import visrec.classifier.AbstractImageClassifier;
import visrec.classifier.ClassificationResult;
import visrec.detection.Detector;
import visrec.detection.ImageDetector;
import visrec.impl.deepnetts.DeepNettsImageClassifier;
import visrec.util.BoundingBox;


/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class MnistDemo {

    public static void main(String[] args) throws IOException {
                
        Properties prop = new Properties();         
        // provide data set properties
        prop.setProperty("visrec.imageWidth",   "28");   
        prop.setProperty("visrec.imageHeight",  "28");        
        prop.setProperty("visrec.labelsFile",   "/home/zoran/datasets/mnist/train/labels.txt");  
        prop.setProperty("visrec.trainingFile", "/home/zoran/datasets/mnist/train/train.txt");  
        
        // provide model properties
        //prop.setProperty("networkArch", "architecture.json");
        prop.setProperty("modelFile", "mnist.dnet");  // save trained model in file at the end
                       
        // provide training properties
        prop.setProperty("visrec.sgd.maxError", "0.03");
        prop.setProperty("visrec.sgd.learningRate", "0.01");        
        
        AbstractImageClassifier imageClassifier = new DeepNettsImageClassifier();         
        imageClassifier.build(prop);
        
        System.out.println("Done building image classifier.");
        
        
        System.out.println("Classifiying images ...");
        List<ClassificationResult<String>> results = imageClassifier.classify(new File("/home/zoran/datasets/mnist/test/someTestImage.png"));        
        System.out.println(results);
        
        System.out.println(results);
        System.out.println("Done.");
        
        
        // show image and frame and outline results
        
    }
    
    
}
