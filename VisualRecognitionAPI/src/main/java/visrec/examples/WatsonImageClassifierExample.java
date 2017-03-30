package visrec.examples;

import deepnets.imgrec.api.DnRecognitionResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import visrec.classifier.ImageClassifier;
import visrec.impl.dl4j.Dl4jImageClassifier;
import visrec.impl.watson.WatsonImageClassifier;
import visrec.util.ImageRecognitionResults;
import visrec.util.RecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class WatsonImageClassifierExample {
    

        
    
    
    public static void main(String[] args) throws IOException {
        
        
        // BUILDING A CUSTOM IMAGE CLASSIFIER
        
        // create watson image classifier using constructor with api key
        ImageClassifier imageClassifier = new WatsonImageClassifier("apiKey", "classifierId"); 
        
        Properties prop = new Properties();
        prop.setProperty("classifierName", "myClassifier");        // this can be optional
        prop.setProperty("bear", "/home/zoran/animals/bear.zip");   // [className => zipped image files] pairs
        prop.setProperty("deer", "/home/zoran/animals/deer.zip");
        prop.setProperty("duck", "/home/zoran/animals/duck.zip");
        prop.setProperty("turtle", "/home/zoran/animals/turtle.zip");    
                            
        imageClassifier.build(prop);
                
                
        // USING IMAGE CLASSIFIER 
        
       // create watson image classifier using constructor with api key
       // ImageClassifier imageClassifier = new WatsonImageClassifier("xxx");    
        
        // classify image, and get results
       List<RecognitionResult> results = imageClassifier.classify(new File("people.jpg")); // todo: additional classification options?
       
       // iterate and print recognition results
       for(RecognitionResult result : results) {
            System.out.println(result);
       }
    }
    
}
