package visrec.examples;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import visrec.classifier.AbstractImageClassifier;
import visrec.classifier.ClassificationResults;
import visrec.impl.deepnetts.DeepNettsImageClassifier;


/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class MnistDemo {

    public static void main(String[] args) throws IOException {
                
        Properties prop = new Properties();         
        // provide data set properties
        prop.setProperty("visrec.imageWidth",   "28");                                              // width of example images   
        prop.setProperty("visrec.imageHeight",  "28");                                              // height of example images
        prop.setProperty("visrec.labelsFile",   "/home/zoran/datasets/mnist/train/labels.txt");     // path to filer with labels (maybe this could be also specifid as visrec.labels="label1,label2,label3")
        prop.setProperty("visrec.trainingFile", "/home/zoran/datasets/mnist/train/train3.txt");     // file with list of training images (contains image paths and corresponding labels)
        
        // prop.setProperty("visrec.model", "networkArchitecture.json");
        prop.setProperty("visrec.model.deepnetts", "mnist1.json");
        // or set individual properties but that would be too heavy from heree
        prop.setProperty("visrec.model.saveToFile", "mnist.dnet");  // save trained model in file at the end
                       
        // training settings
        prop.setProperty("visrec.sgd.maxError", "0.02");
        prop.setProperty("visrec.sgd.learningRate", "0.03");        
        
        AbstractImageClassifier imageClassifier = new DeepNettsImageClassifier();         
        imageClassifier.build(prop);
        
        System.out.println("Done building image classifier.");
                
        System.out.println("Testing image classifier...");
        ClassificationResults results = imageClassifier.classify(new File("/home/zoran/datasets/mnist/train/2/00005.png"));        
        System.out.println(results);                           
    }        
}
