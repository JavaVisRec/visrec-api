package visrec.detection;

import java.util.List;
import visrec.util.BoundingBox;

/**
 * Detect some object in image
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface Detector<IMAGE_CLASS> {
        
        /**
         * Detects some object in specified image
         * 
         * @param image image to search for object
         * @return list of bounding boxes (locations) where the object is detected in image
         */
        public List<BoundingBox> detect(IMAGE_CLASS image);
}
