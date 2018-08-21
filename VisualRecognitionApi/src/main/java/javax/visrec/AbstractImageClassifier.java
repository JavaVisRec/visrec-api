package javax.visrec;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import javax.visrec.internal.ImageFactoryProvider;
import javax.visrec.ml.classification.Classifier;
import javax.visrec.util.Builder;
import javax.visrec.util.ImageFactory;

/**
 * Skeleton abstract class to make it easier to implement image classifier.
 * It provides implementation of Classifier interface for images, along with
 * image factory for specific type of images.
 *
 * @param <IMAGE_CLASS>
 * @param <MODEL_CLASS>
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public abstract class AbstractImageClassifier<IMAGE_CLASS, MODEL_CLASS> implements Classifier<IMAGE_CLASS>, Builder<Classifier> { // could also implement binary classifier

    private ImageFactory<IMAGE_CLASS> imageFactory; // image factory impl for the specified image class
    private MODEL_CLASS model; // the model could be injected from machine learning container?

    private float threshold; // this should ba a part of every classifier

    // TODO: add constructor with model instance
    
    protected AbstractImageClassifier(final Class<IMAGE_CLASS> cls) {
        // zoran: We should avoid using signleton here
        final Optional<ImageFactory<IMAGE_CLASS>> optionalImageFactory = ImageFactoryProvider.getInstance().findImageFactory(cls);
        if (!optionalImageFactory.isPresent()) {
            throw new IllegalArgumentException(String.format("Could not find ImageFactory by '%s'", cls.getName()));
        }
        imageFactory = optionalImageFactory.get();
    }

    public ImageFactory<IMAGE_CLASS> getImageFactory() {
        return imageFactory;
    }

    public Map<String, Float> classify(File file) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(file);
        return classify(image);
    }

    public Map<String, Float> classify(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(inStream);
        return classify(image);
    }

    // do we need this now, when impl is loaded using service provider?
    // Kevin and Zoran disussed: probably not needed now when we have service provider impl, and we dont want to allow user to mess with it
//    public void setImageFactory(ImageFactory<IMAGE_CLASS> imageFactory) {
//        this.imageFactory = imageFactory;
//    }

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
