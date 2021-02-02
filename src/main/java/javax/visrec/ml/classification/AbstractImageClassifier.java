package javax.visrec.ml.classification;

import javax.visrec.ImageFactory;
import javax.visrec.ml.model.ModelProvider;
import javax.visrec.spi.ServiceProvider;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Skeleton abstract class to make it easier to implement image classifier.
 * It provides implementation of Classifier interface for images, along with
 * image factory for specific type of images.
 * This class solves the problem of using various implementation of images and machine learning models in Java,
 * and provides standard Classifier API for clients.
 * <p>
 * By default the type of key in the Map the {@link ImageClassifier} is {@code String}
 *
 * @param <IMAGE_CLASS> class to classify
 * @param <MODEL_CLASS> class of machine learning model
 * @author Zoran Sevarac
 * @since 1.0
 */
public abstract class AbstractImageClassifier<IMAGE_CLASS, MODEL_CLASS> implements ImageClassifier<IMAGE_CLASS>, ModelProvider<MODEL_CLASS> {

    private final ImageFactory<IMAGE_CLASS> imageFactory;
    private MODEL_CLASS model;

    // TODO: this should ba a part of every classifier
    private float threshold = 0.0f;

    protected AbstractImageClassifier(final Class<IMAGE_CLASS> imgCls, final MODEL_CLASS model) {
        final Optional<ImageFactory<IMAGE_CLASS>> optionalImageFactory = ServiceProvider.current()
                .getImageFactoryService()
                .getByImageType(imgCls);
        if (!optionalImageFactory.isPresent()) {
            throw new IllegalArgumentException(String.format("Could not find ImageFactory by '%s'", BufferedImage.class.getName()));
        }
        imageFactory = optionalImageFactory.get();
        setModel(model);
    }

    public ImageFactory<IMAGE_CLASS> getImageFactory() {
        return imageFactory;
    }

    @Override
    public Map<String, Float> classify(Path path) throws ClassificationException {
        IMAGE_CLASS image;
        try {
            image = imageFactory.getImage(path);
            return classify(image);
        } catch (IOException e) {
            throw new ClassificationException("Failed to transform input into a BufferedImage", e);
        }
    }

    @Override
    public Map<String, Float> classify(InputStream inputStream) throws ClassificationException {
        IMAGE_CLASS image;
        try {
            image = imageFactory.getImage(inputStream);
            return classify(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to transform input into a BufferedImage", e);
        }
    }

    // todo: provide get top 1, 3, 5 results; sort and get

    @Override
    public MODEL_CLASS getModel() {
        return model;
    }

    protected final void setModel(MODEL_CLASS model) {
        this.model = Objects.requireNonNull(model, "Model cannot bu null!");
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }
}
