package visrec.recognition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface Recognizer<IMAGE, RESULT> {
    
        public List<RESULT> recognize(IMAGE image);
        
        public List<RESULT> recognize(File file) throws IOException;
        
        public List<RESULT> recognize(URL url) throws IOException;
        
        public List<RESULT> recognize(InputStream inStream) throws IOException;        
    
}
