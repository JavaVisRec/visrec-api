/**
 * Visual Recognition API for Java, JSR381
 * Copyright (C) 2020  Zoran Sevarac, Frank Greco
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
 
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
     * Detects object in specified image and returns a map with detected objects in bounding boxes.
     *
     * @param image image to search for object
     * @return a map of multiple {@link BoundingBox}
     */
    Map<String, List<BoundingBox>> detectObject(T image);

}
