package visrec.detection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import visrec.classifier.ImageClassifier;
import visrec.util.BoundingBox;

/**
 * missing image to search in
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public abstract class AbstractDetector<IMAGE_CLASS> implements Detector<IMAGE_CLASS> {

    ImageClassifier<IMAGE_CLASS, Boolean> imageClassifier; // This should be binary classifier, that can detect some object / image

    
    public AbstractDetector(ImageClassifier<IMAGE_CLASS, Boolean> imageClassifier) {
        this.imageClassifier = imageClassifier;
    }
           
     /**
      * Scan entire image and return positions where object is detected
      * @param image
      * @return 
      */
    @Override
    public abstract List<BoundingBox> detect(IMAGE_CLASS image);
    

    @Override
    public List<BoundingBox> detect(File file) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(file);        
        return detect(image);
    }

    @Override
    public List<BoundingBox> detect(URL url) throws IOException {
        IMAGE_CLASS image =imageClassifier.getImageFactory().getImage(url);        
        return detect(image);   
    }

    @Override
    public List<BoundingBox> detect(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(inStream);        
        return detect(image);   
    }    

    public ImageClassifier<IMAGE_CLASS, Boolean> getImageClassifier() {
        return imageClassifier;
    }
    
    
    
}
