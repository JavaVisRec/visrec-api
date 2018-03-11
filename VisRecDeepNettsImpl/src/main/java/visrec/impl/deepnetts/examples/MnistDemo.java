package visrec.impl.deepnetts.examples;

import visrec.impl.deepnetts.DeepNettsImageClassifier;

import javax.visrec.AbstractImageClassifier;
import javax.visrec.ml.classification.ClassificationResults;
import java.io.File;
import java.io.IOException;
import java.util.Properties;


/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class MnistDemo {

    public static void main(String[] args) throws IOException {
                
        Properties props = new Properties();         
        // provide data set properties
        props.setProperty("visrec.imageWidth",   "28");                                              // width of example images   
        props.setProperty("visrec.imageHeight",  "28");                                              // height of example images
        props.setProperty("visrec.labelsFile",   "/home/zoran/datasets/mnist/train/labels.txt");     // path to filer with labels (maybe this could be also specifid as visrec.labels="label1,label2,label3")
        props.setProperty("visrec.trainingFile", "/home/zoran/datasets/mnist/train/train3.txt");     // file with list of training images (contains image paths and corresponding labels)
        
        // prop.setProperty("visrec.model", "networkArchitecture.json");
        props.setProperty("visrec.model.deepnetts", "mnist1.json");
        // or set individual properties but that would be too heavy from heree
        props.setProperty("visrec.model.saveToFile", "mnist.dnet");  // save trained model to file at the end
                       
        // training settings visrec.deepnetts.optimizationType=adagrad etc.
        props.setProperty("visrec.sgd.maxError", "0.02");
        props.setProperty("visrec.sgd.learningRate", "0.03");        
        
        AbstractImageClassifier imageClassifier = new DeepNettsImageClassifier();         
        imageClassifier.build(props);
        
        System.out.println("Done building image classifier.");
                
        System.out.println("Testing image classifier...");
        ClassificationResults results = imageClassifier.classify(new File("/home/zoran/datasets/mnist/train/2/00005.png"));        
        System.out.println(results);                           
    }        
}
