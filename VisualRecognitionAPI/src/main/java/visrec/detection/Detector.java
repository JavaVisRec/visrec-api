package visrec.detection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import visrec.util.BoundingBox;

/**
 * Detect some object in image
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface Detector<IMAGE_CLASS> {
        
        /**
         * Detects object in specified image
         * 
         * @param image image to search for object
         * @return list of bounding boxes (locations) where the object is detected in image
         */
        public List<BoundingBox> detect(IMAGE_CLASS image);
        
        public List<BoundingBox> detect(File file) throws IOException;
        
        public List<BoundingBox> detect(URL url) throws IOException;
        
        public List<BoundingBox> detect(InputStream inStream) throws IOException;
}
