package javax.visrec;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.visrec.ml.classification.Classifier;
import javax.visrec.util.BufferedImageFactory;
import javax.visrec.util.Builder;
import javax.visrec.util.ImageFactory;

/**
 * Skeleton abstract class to make it easier to implement image classifier.
 * Solves problem of using difefrent types of images and different machine learning models.
 * 
 * Question: What is more intuitive to vace Imasge class od model class first?
 * 
 * @param <IMAGE_CLASS>
 * @param <MODEL_CLASS>
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public abstract class AbstractImageClassifier<IMAGE_CLASS, MODEL_CLASS> implements Classifier<IMAGE_CLASS>, Builder<Classifier> { // could also implement binary classifier

    private ImageFactory<IMAGE_CLASS> imageFactory; // get image factory for the specified image class   
    private MODEL_CLASS model; // th emodel could be injected from machine learning container
    
    private float threshold;

    public AbstractImageClassifier(MODEL_CLASS model) {
        setModel(model);
      // instantiate image factory whuch coresponds to specified IMAGE_CLASS - use some lookup/registru/service provider mechanism
      // use https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html
      // https://www.concretepage.com/java/serviceloader-java-example
      // https://stackoverflow.com/questions/10304100/serviceloader-to-find-implementations-of-an-interface
      // http://www.oracle.com/technetwork/articles/javase/extensible-137159.html
      // https://dzone.com/articles/simple-dependency-injection-wi
      // http://literatejava.com/extensibility/java-serviceloader-extensible-applications/
      // https://stackoverflow.com/questions/10118198/how-to-use-serviceregistry/36781744#36781744
        imageFactory = (ImageFactory<IMAGE_CLASS>) new BufferedImageFactory(); // get factory that corresponds to specified type of image frm some registry
    }
    
    public ImageFactory<IMAGE_CLASS> getImageFactory() {
        return imageFactory;
    }
    
    public Map<String, Float>  classify(File file) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(file);
        return classify(image);            
    }

//    public ClassificationResults<BoundingBox> classify(URL url) throws IOException {
//        IMAGE_CLASS image = imageFactory.getImage(url);
//         return classify(image);            
//    }

    public Map<String, Float> classify(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(inStream);
        return classify(image);       
    }

    public void setImageFactory(ImageFactory<IMAGE_CLASS> imageFactory) {
        this.imageFactory = imageFactory; 
    }

    public MODEL_CLASS getModel() {
        return model;
    }
    
    protected final void setModel(MODEL_CLASS model) {
        this.model = model;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }    
}