package visrec.annotation;

import visrec.classifier.ClassificationResults;
import visrec.util.BoundingBox;

/**
 * Interface for annotating images.
 * 
 * List all objects found in specified images, with class labels and scores
 * Recognize one or several learned objects or object classes with their 2D positions.
 * Performs object  classification + localization  task
 * 
 * @param <IMAGE_CLASS> Type of the input images
 */
public interface Annotator<IMAGE_CLASS>  {
        
    public ClassificationResults<BoundingBox> annotate(IMAGE_CLASS image);
               
}