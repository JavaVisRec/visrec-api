package visrec.impl.watson;

import java.io.File;
import java.io.IOException;
import visrec.classifier.ImageClassifier;
import visrec.util.ImageRecognitionResults;
import visrec.util.ImageRecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class WatsonImageClassifierExample {
    
    public static void main(String[] args) throws IOException {
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
