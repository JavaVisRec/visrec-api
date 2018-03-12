package javax.visrec.detection;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.visrec.AbstractImageClassifier;
import javax.visrec.ml.classification.ClassificationResult;
import javax.visrec.util.BoundingBox;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class SimpleObjectDetector extends AbstractObjectDetector<BufferedImage> {
    
    private double threshold = 0.5;
    
    public SimpleObjectDetector(AbstractImageClassifier<BufferedImage, Boolean> classifier) {
        super(classifier);
    }

    /**
     * Scan image using brute force sliding window and return positions where classifier
     * returns score greater then threshold.
     * 
     * get width and height of the image
     * and scan image with classifier - apply classifier to each position
     * This is trivial implementation and should be replaced with something better
     * 
     * @param image
     * @return 
     */
    @Override
    public Map detectObject(BufferedImage image) {       
        
        Map<String, Float>  results = null;
        int boxWidth = 64, boxHeight = 64;
        
        for (int y = 0; y < image.getHeight()-boxHeight; y++) {
            for (int x = 0; x < image.getWidth()-boxWidth; x++) {
                    
                   results =  getImageClassifier().classify(image.getSubimage(x, y, boxWidth, boxHeight));     
                   // sort rsults and get top 5
//                   for(ClassificationResult rr : results2.getTopKResults(5)) {
//                       if (rr.getScore() > threshold) {
//                           BoundingBox bbox = new BoundingBox(rr.getClassLabel(), rr.getScore(), x, y, boxWidth, boxHeight);
//                           results.put(bbox);
//                       }
//                   }                   
            }
        }
                
        Map<String, Float>  sortedResults = new TreeMap(results);
        return sortedResults;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }        
}