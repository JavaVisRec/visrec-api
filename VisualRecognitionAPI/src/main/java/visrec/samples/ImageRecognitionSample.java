package visrec.samples;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import visrec.classifier.Classifier;
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
        Classifier<BufferedImage, String> someClassifier = null;
        // someClassifier.buildClassifier(data); // train/build classifier here
        
        // wrap the image classifier into image recognition interface
        Recognizer<BufferedImage, String> imgRecognition = new ImageRecognitionProvider<BufferedImage, String>(someClassifier);
        
        // get some image from file
        BufferedImage someimage = ImageIO.read(new File("imageFile.png"));
        
        // use the image recognition service        
        imgRecognition.recognize(someimage);  // get recognition results in some standardized way
    }
    
}
