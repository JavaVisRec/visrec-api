package javax.visrec;

import javax.visrec.ml.ClassificationException;
import javax.visrec.ml.classification.ImageClassifier;
import javax.visrec.spi.ServiceProvider;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Skeleton abstract class to make it easier to implement image classifier.
 * It provides implementation of Classifier interface for images, along with
 * image factory for specific type of images.
 * This class solves the problem of using various implementation of images and machine learning models in Java,
 * and provides standard Classifier API for clients.
 *
 * By default the type of key in the Map the {@link ImageClassifier} is {@code String}
 *
 * @author Zoran Sevarac
 *
 * @param <MODEL_CLASS> class of machine learning model
 */
public abstract class AbstractImageClassifier<MODEL_CLASS> implements ImageClassifier { // could also implement binary classifier

    private ImageFactory<BufferedImage> imageFactory; // image factory impl for the specified image class
    private MODEL_CLASS model; // the model could be injected from machine learning container?

    private float threshold; // this should ba a part of every classifier

    protected AbstractImageClassifier(final MODEL_CLASS model) {
        final Optional<ImageFactory<BufferedImage>> optionalImageFactory = ServiceProvider.current()
                .getImageFactoryService()
                .getByImageType(BufferedImage.class);
        if (!optionalImageFactory.isPresent()) {
            throw new IllegalArgumentException(String.format("Could not find ImageFactory by '%s'", BufferedImage.class.getName()));
        }
        imageFactory = optionalImageFactory.get();
        setModel(model);
    }

    public ImageFactory<BufferedImage> getImageFactory() {
        return imageFactory;
    }

    @Override
    public Map<String, Float> classify(File file) throws ClassificationException {
        BufferedImage image;
        try {
            image = imageFactory.getImage(file);
        } catch (IOException e) {
            throw new ClassificationException("Couldn't transform input into a BufferedImage", e);
        }
        return classify(image);
    }

    @Override
    public Map<String, Float> classify(InputStream inputStream) throws ClassificationException {
        BufferedImage image;
        try {
            image = imageFactory.getImage(inputStream);
        } catch (IOException e) {
            throw new ClassificationException("Couldn't transform input into a BufferedImage", e);
        }
        return classify(image);
    }
    
    // todo: provide get top 1, 3, 5 results; sort and get

    // do we need this now, when impl is loaded using service provider?
    // Kevin and Zoran disussed: probably not needed now when we have service provider impl, and we dont want to allow user to mess with it
//    public void setImageFactory(ImageFactory<IMAGE_CLASS> imageFactory) {
//        this.imageFactory = imageFactory;
//    }

    public MODEL_CLASS getModel() {
        return model;
    }

    public void setModel(MODEL_CLASS model) {
        this.model = Objects.requireNonNull(model, "Model cannot bu null!");         
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }
}
