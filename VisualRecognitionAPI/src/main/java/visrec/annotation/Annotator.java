package visrec.annotation;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import visrec.util.BoundingBox;

/**
 * Interface for annotating images
 */
public interface Annotator<IMAGE>  {
        
    public List<BoundingBox> annotate(IMAGE image);
    
    public List<BoundingBox> annotate(File file);
    
    public List<BoundingBox> annotate(URL url);
    
    public List<BoundingBox> annotate(InputStream inStream);
    
        
}
