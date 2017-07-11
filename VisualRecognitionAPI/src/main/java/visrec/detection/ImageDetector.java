package visrec.detection;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import visrec.classifier.AbstractImageClassifier;
import visrec.classifier.ClassificationResult;
import visrec.util.BoundingBox;
import visrec.util.RecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ImageDetector extends AbstractDetector<BufferedImage> {
    
    private double threshold = 0.5;
    
    public ImageDetector(AbstractImageClassifier<BufferedImage, Boolean> classifier) {
        super(classifier);
    }

    /**
     * Scan image using brute force sliding window and return positions where classifier
     * returns score greater then threshold
     * get width and height of the image
     * and scan image with classifier - apply classifier to each position
     * This is trivial implementation and should be replaced with something better
     * 
     * @param image
     * @return 
     */
    @Override
    public List<BoundingBox> detect(BufferedImage image) {       
        List<BoundingBox> results = new ArrayList<>();
        
        int boxWidth = 64, boxHeight = 64;
        
        for (int y = 0; y < image.getHeight()-boxHeight; y++) {
            for (int x = 0; x < image.getWidth()-boxWidth; x++) {
                    
                   List<ClassificationResult<String>> results2 = getImageClassifier().classify(image.getSubimage(x, y, boxWidth, boxHeight));     
                   for(ClassificationResult<String> rr : results2) {
                       System.out.println(x+","+y);
                       if (rr.getScore() > threshold) {
                           System.out.println("Evooooooooooooooooooooo gaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!!!");
                           BoundingBox bbox = new BoundingBox(x, y, boxWidth, boxHeight);   // TODO: this cannnot be hardcoded, get this from model
                           bbox.setLabel(rr.getClassLabel());
                           bbox.setScore(rr.getScore());
                           results.add(bbox);
                       }
                   }                   
            }
        }
                
        return results;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
    
    

}