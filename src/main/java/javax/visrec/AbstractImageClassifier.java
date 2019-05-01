package javax.visrec;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.visrec.ml.classification.Classifier;
import javax.visrec.spi.ServiceProvider;
import javax.visrec.util.Builder;

/**
 * Skeleton abstract class to make it easier to implement image classifier.
 * It provides implementation of Classifier interface for images, along with
 * image factory for specific type of images.
 * This class solves the problem of using various implementation of images and machine learning models in Java,
 * and provides standard Classifier API for clients.
 *
 * By default the type of key in the Map the {@link Classifier} is {@code String}
 *
 * @author Zoran Sevarac
 *
 * @param <IMAGE_CLASS> class of images
 * @param <MODEL_CLASS> class of machine learning model
 *
 *
 */
public abstract class AbstractImageClassifier<IMAGE_CLASS, MODEL_CLASS> implements Classifier<IMAGE_CLASS, String> { // could also implement binary classifier

    private ImageFactory<IMAGE_CLASS> imageFactory; // image factory impl for the specified image class
    private MODEL_CLASS model; // the model could be injected from machine learning container?

    private float threshold; // this should ba a part of every classifier

    // TODO: add constructor with model instance
    public AbstractImageClassifier(MODEL_CLASS model) {
        setModel(model);
    }

    protected AbstractImageClassifier(final Class<IMAGE_CLASS> cls) {
        final Optional<ImageFactory<IMAGE_CLASS>> optionalImageFactory = ServiceProvider.current()
                .getImageFactoryService()
                .getByImageType(cls);
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
        Objects.requireNonNull(model, "Model cannot bu null!");
        this.model = model;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }
}
