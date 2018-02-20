package visrec.classifier;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import visrec.util.BufferedImageFactory;
import visrec.util.Builder;
import visrec.util.ImageFactory;

/**
 * Skeleton abstract class to make it easier to implemen image classifier.
 * 
 * @param <IMAGE_CLASS>
 * @param <MODEL_CLASS>
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public abstract class AbstractImageClassifier<IMAGE_CLASS, MODEL_CLASS> implements Classifier<IMAGE_CLASS, String>, Builder<Classifier> { // could also implement binary classifier

    private ImageFactory<IMAGE_CLASS> imageFactory; // get image factory for the specified image class   
    private MODEL_CLASS model; // th emodel could be injected from machine learning container
    
    private float threshold;

    public AbstractImageClassifier() {
      // instantiate image factory whuch coresponds to specified IMAGE_CLASS
        imageFactory = (ImageFactory<IMAGE_CLASS>) new BufferedImageFactory();
    }
    
    public ImageFactory<IMAGE_CLASS> getImageFactory() {
        return imageFactory;
    }
    
    public ClassificationResults<ClassificationResult>  classify(File file) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(file);
        return classify(image);            
    }

//    public ClassificationResults<BoundingBox> classify(URL url) throws IOException {
//        IMAGE_CLASS image = imageFactory.getImage(url);
//         return classify(image);            
//    }

    public ClassificationResults classify(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(inStream);
        return classify(image);       
    }

    public void setImageFactory(ImageFactory<IMAGE_CLASS> imageFactory) {
        this.imageFactory = imageFactory; 
    }

    public MODEL_CLASS getModel() {
        return model;
    }
    
    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }    
}