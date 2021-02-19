package javax.visrec.ml.detection;

import java.util.List;
import java.util.Map;

/**
 * Interface to perform object detection in image.
 * Returns a map of object labels/classes and corresponding location in image outlined by BoundingBox-es
 *
 * @param <T> Class used to represent image that will be analyzed
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
