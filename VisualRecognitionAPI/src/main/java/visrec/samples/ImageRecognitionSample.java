package visrec.samples;

import java.awt.image.BufferedImage;
import visrec.classifier.Classifier;
import visrec.tasks.ImageRecognition;
import visrec.tasks.ImageRecognitionProvider;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ImageRecognitionSample {

    public static void main(String[] args) {        
        Classifier<BufferedImage, String> someClassifier;
        
        ImageRecognition<BufferedImage, String> ir = new ImageRecognitionProvider<BufferedImage, String>(someClassifier);
        ir.recognizeImage((BufferedImage) ir);
    }
    
}
