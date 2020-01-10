package javax.visrec.spi;

import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.ml.classification.BinaryClassifier;
import javax.visrec.ml.classification.Classifier;
import javax.visrec.ml.classification.ImageClassifier;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Service to provide the correct {@link Classifier} implementation.
 *
 * @author Kevin Berendsen
 * @since 1.0
 */
public final class ClassifierCreatorService {

    private Map<Class<?>, ImageClassifierCreator<?>> imageClassifierCreators;
    private Map<Class<?>, BinaryClassifierCreator<?>> binaryClassifierCreators;

    private static ClassifierCreatorService instance;
    static ClassifierCreatorService getInstance() {
        if (instance == null) {
            instance = new ClassifierCreatorService();
        }
        return instance;
    }

    private ClassifierCreatorService() {
        // Prevent instantiation
    }

    /**
     * Creates a new {@link ImageClassifier} by providing the {@link ImageClassifier.BuildingBlock} to tune
     * the implementation's image classifier.
     *
     * @param block {@link ImageClassifier.BuildingBlock} is provided to tune the building of the image classifier.
     * @return {@link ImageClassifier}
     * @throws ClassifierCreationException if the classifier can not be created due to any reason.
     */
    public <T> ImageClassifier<T> createImageClassifier(ImageClassifier.BuildingBlock<T> block) throws ClassifierCreationException {
        if (imageClassifierCreators == null) {
            imageClassifierCreators = new HashMap<>();
            for (ImageClassifierCreator<?> classifierCreator : ServiceLoader.load(ImageClassifierCreator.class)) {
                imageClassifierCreators.put(classifierCreator.getImageClass(), classifierCreator);
            }
        }

        ImageClassifierCreator<?> creator = imageClassifierCreators.get(block.getImageClass());
        if (creator == null) {
            throw new ClassifierCreationException("Unsupported image class");
        }

        @SuppressWarnings("unchecked")
        ImageClassifierCreator<T> castedCreator = (ImageClassifierCreator<T>) creator;
        return castedCreator.create(block);
    }

    /**
     * Creates a new {@link BinaryClassifier} by providing the {@link BinaryClassifier.BuildingBlock} to tune
     * the implementation's binary classifier.
     *
     * @param block {@link BinaryClassifier.BuildingBlock} is provided to tune the building of the binary classifier.
     * @return {@link BinaryClassifier}
     * @throws ClassifierCreationException if the classifier can not be created due to any reason.
     */
    public <T> BinaryClassifier<T> createBinaryClassifier(BinaryClassifier.BuildingBlock<T> block) throws ClassifierCreationException {
        if (binaryClassifierCreators == null) {
            binaryClassifierCreators = new HashMap<>();
            for (BinaryClassifierCreator<?> classifierCreator : ServiceLoader.load(BinaryClassifierCreator.class)) {
                binaryClassifierCreators.put(classifierCreator.getTargetClass(), classifierCreator);
            }
        }

        BinaryClassifierCreator<?> creator = binaryClassifierCreators.get(block.getTargetClass());
        if (creator == null) {
            throw new ClassifierCreationException("Unsupported target class");
        }

        @SuppressWarnings("unchecked")
        BinaryClassifierCreator<T> castedCreator = (BinaryClassifierCreator<T>) creator;
        return castedCreator.create(block);
    }
}
