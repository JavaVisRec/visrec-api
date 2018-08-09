package javax.visrec.ml.detection;

import javax.visrec.ml.classification.BoundingBox;
import java.util.List;
import java.util.Map;

/**
 * Detect some object in image
 *
 * TODO Is this on image X? Does this image contains X?
 *
 * @author Zoran Sevarac
 * @param <T> Class used to represent image
 * @since 1.0
 */
@FunctionalInterface
public interface ObjectDetector<T> {

    /**
     * Detects object in specified image
     *
     * @param image image to search for object
     * @return a map of multiple {@link BoundingBox}
     */
    Map<String, List<BoundingBox>> detectObject(T image);

}
