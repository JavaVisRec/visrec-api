package visrec.detection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import visrec.classifier.AbstractImageClassifier;
import visrec.classifier.ClassificationResults;
import visrec.util.BoundingBox;

/**
 * missing image to search in
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public abstract class AbstractDetector<IMAGE_CLASS> implements Detector<IMAGE_CLASS> {

    AbstractImageClassifier<IMAGE_CLASS, Boolean> imageClassifier; // This should be binary classifier, that can detect some object / image
    
    public AbstractDetector(AbstractImageClassifier<IMAGE_CLASS, Boolean> imageClassifier) {
        this.imageClassifier = imageClassifier;
    }
           
     /**
      * Scan entire image and return positions where object is detected
      * @param image
      * @return 
      */
    @Override
    public abstract ClassificationResults<BoundingBox> detect(IMAGE_CLASS image);
    
    public ClassificationResults detect(File file) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(file);        
        return detect(image);
    }

    public ClassificationResults detect(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(inStream);        
        return detect(image);   
    }    

    /**
     * Subclasses should use ths method to use the inderlying image classifier
     * @return 
     */
    public AbstractImageClassifier<IMAGE_CLASS, Boolean> getImageClassifier() {
        return imageClassifier;
    }
            
}