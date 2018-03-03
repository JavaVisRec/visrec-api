package javax.visrec.detection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.visrec.AbstractImageClassifier;
import javax.visrec.ml.classification.ClassificationResults;
import javax.visrec.util.BoundingBox;

/**
 * missing image to search in
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public abstract class AbstractObjectDetector<IMAGE_CLASS> implements ObjectDetector<IMAGE_CLASS> {

    AbstractImageClassifier<IMAGE_CLASS, Boolean> imageClassifier; // This should be binary classifier, that can detectObject some object / image
    
    public AbstractObjectDetector(AbstractImageClassifier<IMAGE_CLASS, Boolean> imageClassifier) {
        this.imageClassifier = imageClassifier;
    }
           
     /**
      * Scan entire image and return positions where object is detected
      * @param image
      * @return 
      */
    @Override
    public abstract ClassificationResults<BoundingBox> detectObject(IMAGE_CLASS image);
    
    public ClassificationResults detect(File file) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(file);        
        return detectObject(image);
    }

    public ClassificationResults detect(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(inStream);        
        return detectObject(image);   
    }    

    /**
     * Subclasses should use ths method to use the inderlying image classifier
     * @return 
     */
    public AbstractImageClassifier<IMAGE_CLASS, Boolean> getImageClassifier() {
        return imageClassifier;
    }
            
}