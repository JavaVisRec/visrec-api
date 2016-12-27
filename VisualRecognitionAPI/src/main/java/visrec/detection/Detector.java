package visrec.detection;

import java.util.List;
import visrec.util.BoundingBox;

/**
 * Detect some object in image
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface Detector<IMAGE_CLASS> {
        public List<BoundingBox> detect(IMAGE_CLASS image);
}
