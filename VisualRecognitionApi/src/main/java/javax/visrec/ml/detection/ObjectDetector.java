package javax.visrec.ml.detection;

import javax.visrec.ml.classification.BoundingBox;
import java.util.List;
import java.util.Map;

/**
 * Detect some object in image
 *
 * TODO Is this on image X? Does this image contains X?
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <IMAGE_CLASS> Class used to represent image
 * @since 1.0
 */
@FunctionalInterface
public interface ObjectDetector<IMAGE_CLASS> {

    /**
     * Detects object in specified image
     *
     * @param image image to search for object
     * @return
     */
    Map<String, List<BoundingBox>> detectObject(IMAGE_CLASS image);

}
