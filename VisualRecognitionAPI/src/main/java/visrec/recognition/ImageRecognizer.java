package visrec.recognition;

import java.awt.image.BufferedImage;
import visrec.classifier.AbstractImageClassifier;
import visrec.util.RecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ImageRecognizer extends AbstractRecognizer<BufferedImage> {
    
    private double threshold;
        
    public ImageRecognizer(AbstractImageClassifier<BufferedImage, RecognitionResult> classifier) {
        super(classifier);
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
    
    

}
