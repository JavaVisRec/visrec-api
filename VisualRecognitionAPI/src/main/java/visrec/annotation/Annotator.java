package visrec.annotation;

import java.util.List;
import visrec.util.BoundingBox;

/**
 * Interface for annotating images
 */
public interface Annotator<IMAGE>  {
    
    
    public List<BoundingBox> annotate(IMAGE image);
        
}
