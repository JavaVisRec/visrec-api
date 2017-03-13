package visrec.detection;

import java.awt.image.BufferedImage;
import java.util.List;
import visrec.classifier.ImageClassifier;
import visrec.util.BoundingBox;
import visrec.util.ImageRecognitionResults;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ImageDetectorProvider extends AbstractDetector<BufferedImage> {
    
    public ImageDetectorProvider(ImageClassifier<BufferedImage, Boolean> classifier) {
        super(classifier);
    }

    @Override
    public List<BoundingBox> detect(BufferedImage image) {
        // scan image using brute force and return positions where classifier returns true
        // get width and height of the image
        // and scan image widh classifier - apply classifier to each position
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                   ImageRecognitionResults results = getImageClassifier().classify(image);
                
            }
        }
        
        
        return null;
        //return (List<RESULT>) classifier.classify(image);
    }

}