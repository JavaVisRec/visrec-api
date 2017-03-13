package visrec.classifier;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import visrec.util.BoundingBox;
import visrec.util.BufferedImageFactory;
import visrec.util.ImageFactory;
import visrec.util.ImageRecognitionResults;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <IMAGE_CLASS>
 */
public abstract class ImageClassifier<IMAGE_CLASS, MODEL_CLASS> implements Classifier<IMAGE_CLASS, ImageRecognitionResults> {

    private ImageFactory<IMAGE_CLASS> imageFactory; // get image factory for the cpecified image class
   // Classifier<IMAGE_CLASS, String> classifier; // probably not image class but some matrix/vector representation of image. Which library to use? not used for watson ...
    
    private MODEL_CLASS model;

    public ImageClassifier() {
        // instantiate image factory whuch coresponds to specified IMAGE_CLASS
      //  imageFactory = new BufferedImageFactory();
    }
    
    public ImageFactory<IMAGE_CLASS> getImageFactory() {
        return imageFactory;
    }
       
    @Override
    public abstract ImageRecognitionResults classify(IMAGE_CLASS sample) ;  // OVAJ SE I NE KORISTI
    

    public ImageRecognitionResults classify(File file) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(file);
        return classify(image);            
    }

    public ImageRecognitionResults classify(URL url) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(url);
         return classify(image);            
    }

    public ImageRecognitionResults classify(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(inStream);
         return classify(image);       
    }

    public void setImageFactory(ImageFactory<IMAGE_CLASS> imageFactory) {
        this.imageFactory = imageFactory; 
    }

    public MODEL_CLASS getModel() {
        return model;
    }
    
    

}
