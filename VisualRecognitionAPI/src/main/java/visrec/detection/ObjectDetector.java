package visrec.detection;

import visrec.classifier.ClassificationResults;
import visrec.util.BoundingBox;

/**
 * Detect some object in image
 * 
 * Is this on image X?
 * Does this image contains X?
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <IMAGE_CLASS> Class used to represent image
 */
@FunctionalInterface
public interface ObjectDetector<IMAGE_CLASS> {
        
        /**
         * Detects object in specified image
         * 
         * @param image image to search for object
         * @return 
         */
        public ClassificationResults<BoundingBox> detectObject(IMAGE_CLASS image); 
        
        
}
