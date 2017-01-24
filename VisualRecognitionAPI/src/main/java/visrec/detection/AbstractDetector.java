package visrec.detection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import visrec.classifier.Classifier;
import visrec.util.BoundingBox;
import visrec.util.ImageFactory;

/**
 * missing image to search in
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public abstract class AbstractDetector<IMAGE_CLASS> implements Detector<IMAGE_CLASS> {

   // Classifier<IMAGE_CLASS, Boolean> classifier; // This should be binary classifier, that can detect some object / image
    // this should hold iage classifier
    ImageFactory<IMAGE_CLASS> imageFactory;

    public AbstractDetector() {
        // get the image factory for the specified class (Service Loader? )
       // imageFactory = ImageFactory.getForClass(IMAGE_CLASS);
    }
    
//    public AbstractDetector(Classifier<IMAGE_CLASS, Boolean> classifier) {
//        this.classifier = classifier;
//        // get the image factory for the specified class (Service Loader )
//    }    
           
    @Override
    public abstract List<BoundingBox> detect(IMAGE_CLASS image);
    
    // detect for Image, File, Url, Stream

    @Override
    public List<BoundingBox> detect(File file) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(file);        
        return detect(image);
    }

    @Override
    public List<BoundingBox> detect(URL url) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(url);        
        return detect(image);   
    }

    @Override
    public List<BoundingBox> detect(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(inStream);        
        return detect(image);   
    }

    public void setImageFactory(ImageFactory<IMAGE_CLASS> imageFactory) {
        this.imageFactory = imageFactory;
    }
    
    

    
}
