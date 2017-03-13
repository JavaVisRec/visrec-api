package visrec.examples;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import visrec.classifier.Classifier;
import visrec.classifier.ImageClassifier;
import visrec.recognition.Recognizer;
import visrec.recognition.ImageRecognitionProvider;

/**
 * Example how image recognition service based on classifier can be created and used
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ImageRecognitionSample {

    public static void main(String[] args) throws IOException {        
        // Create an image classifier based on standard classifier interface
        ImageClassifier<BufferedImage, String> someClassifier = null;
        // someClassifier.buildClassifier(data); // train/build classifier here -- use factory ImageClassifier
        
        // wrap the image classifier into image recognition interface
        Recognizer<BufferedImage, String> imgRecognition = new ImageRecognitionProvider<BufferedImage, String>(someClassifier);
        
        // get some image from file
        BufferedImage someimage = ImageIO.read(new File("imageFile.png"));
        
        // use the image recognition service        
        imgRecognition.recognize(someimage);  // get recognition results in some standardized way
    }
    
}
