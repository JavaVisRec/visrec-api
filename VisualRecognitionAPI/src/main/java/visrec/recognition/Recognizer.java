package visrec.recognition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import visrec.classifier.ClassificationResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface Recognizer<IMAGE_CLASS> {
    
        public List<ClassificationResult<String>> recognize(IMAGE_CLASS image);
        
        public List<ClassificationResult<String>> recognize(File file) throws IOException;
        
        public List<ClassificationResult<String>> recognize(URL url) throws IOException;
        
        public  List<ClassificationResult<String>> recognize(InputStream inStream) throws IOException;        
    
}
