package visrec.examples;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.datavec.image.loader.ImageLoader;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import visrec.classifier.ImageClassifier;
import visrec.impl.dl4j.Dl4jImageClassifier;
import visrec.util.ImageRecognitionResults;
import visrec.util.ImageRecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class Dl4jImageClassifierExample {

    
    public static void main(String[] args) throws IOException {
        
        // load trained dl4j LeNet 
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
        
  
        // create watson image classifier using constructor with api key
   //    ImageClassifier imageClassifier = new Dl4jImageClassifier("LeNetMultiLayerNetwork.zip");    
       ImageClassifier imageClassifier = new Dl4jImageClassifier(neuralNet);    
        
        // classify image, and get results
       ImageRecognitionResults results = imageClassifier.classify(new File("00060.png")); // todo: additional classification options?
       
       // iterate and print recognition results
       for(ImageRecognitionResult result : results) {
            System.out.println(result);
       }  
        
    }
    
}
