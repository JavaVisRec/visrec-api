package javax.visrec.detection;

import java.util.List;
import java.util.Map;
import javax.visrec.util.BoundingBox;

/**
 * Detect object in image
 * 
 * Is this on image X?
 * Does this image contains X?
 * 
 * TODO: implement FaceDetector using openimagej
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <IMAGE_CLASS> Class used to represent image
 */
@FunctionalInterface
public interface ObjectDetector<IMAGE_CLASS> {
        
        /**
         * Detects object in specified image
         *          * 
         * @param image image to scan for known object
         * @return map with object labels and list of bounding boxes which contain locations and probabilities
         */
        public Map<String, List<BoundingBox>> detectObject(IMAGE_CLASS image); 
        
        
}
