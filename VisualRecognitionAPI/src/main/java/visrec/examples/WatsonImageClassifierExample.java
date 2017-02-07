package visrec.examples;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import visrec.classifier.ImageClassifier;
import visrec.impl.dl4j.Dl4jImageClassifier;
import visrec.impl.watson.WatsonImageClassifier;
import visrec.util.ImageRecognitionResults;
import visrec.util.ImageRecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class WatsonImageClassifierExample {
    
    public static ImageClassifier buildWatsonImageClassifier() {
        WatsonImageClassifier imageClassifier = new WatsonImageClassifier("9a576187233d8b7918c6165b8f128466a7420c62"); 
        
        
        Properties properties = new Properties();
        // provide images and apikey here - classes and files - what if there is a 1000 classes?
//        properties.put("imagesPath", "/home/zoran/animals");
//        properties.put("imageWidth", "100");
//        properties.put("imageHeight", "100");
                     
        imageClassifier.buildClassifier(properties);
        
        return imageClassifier;
    }
        
    
    
    public static void main(String[] args) throws IOException {
        
        ImageClassifier customImageClassifier  = buildWatsonImageClassifier();
        
        
        // create watson image classifier using constructor with api key
       ImageClassifier imageClassifier = new WatsonImageClassifier("9a576187233d8b7918c6165b8f128466a7420c62");    
        
        // classify image, and get results
       ImageRecognitionResults results = imageClassifier.classify(new File("people.jpg")); // todo: additional classification options?
       
       // iterate and print recognition results
       for(ImageRecognitionResult result : results) {
            System.out.println(result);
       }
    }
    
}
