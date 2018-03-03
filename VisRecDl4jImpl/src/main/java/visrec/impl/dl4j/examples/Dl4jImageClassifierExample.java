package visrec.impl.dl4j.examples;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import visrec.classifier.AbstractImageClassifier;
import visrec.classifier.ClassificationResult;
import visrec.classifier.ClassificationResults;
import visrec.impl.dl4j.Dl4jImageClassifier;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class Dl4jImageClassifierExample {
    
    public static void main(String[] args) throws IOException {
        
        // Build image classifier
        AbstractImageClassifier imageClassifier = new Dl4jImageClassifier(); 
        
        Properties prop = new Properties();        
        prop.put("imagesPath", "/home/zoran/animals");  // path to directory with images. Containes subfolders with images named as corresponding classes
        prop.put("imageWidth", "100");
        prop.put("imageHeight", "100");
                    
        imageClassifier.build(prop);
        
        
 /*       // load trained dl4j LeNet 
        //wrap this network into classifier interface
        MultiLayerNetwork neuralNet = ModelSerializer.restoreMultiLayerNetwork("LeNetMultiLayerNetwork.zip");
//        
//        // load image and convert it to matrix
//        BufferedImage someimage = ImageIO.read(new File("00060.png"));
//        ImageLoader imageLoader = new ImageLoader();                
//        INDArray input =  imageLoader.asRowVector(someimage);     
//        
//        // feed netwrk with image       
//        INDArray output = neuralNet.output(input);
//        System.out.println(output); // interpret results
        
  
        
   //    AbstractImageClassifier imageClassifier = new Dl4jImageClassifier("LeNetMultiLayerNetwork.zip");    // load trained model from file
       AbstractImageClassifier imageClassifier = new Dl4jImageClassifier(neuralNet);     // provide instance of the trained model or model that should be trained
*/        
        // classify image, and get results
  
        // USING IMAGE CLASSIFIER
        
       ClassificationResults<ClassificationResult> results = imageClassifier.classify(new File("00060.png")); // todo: additional classification options?
       
       // iterate and print recognition results
       for(ClassificationResult result : results.getTopKResults(5)) {
            System.out.println(result);
       }  

        
    }
    
}
