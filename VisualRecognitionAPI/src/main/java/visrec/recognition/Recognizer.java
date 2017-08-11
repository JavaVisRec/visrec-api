package visrec.recognition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import visrec.classifier.ClassificationResult;
import visrec.classifier.ClassificationResults;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface Recognizer<IMAGE_CLASS> {
    
        public ClassificationResults recognize(IMAGE_CLASS image);
        
        public ClassificationResults recognize(File file) throws IOException;
        
        public ClassificationResults recognize(URL url) throws IOException;
        
        public ClassificationResults recognize(InputStream inStream) throws IOException;        
    
}
