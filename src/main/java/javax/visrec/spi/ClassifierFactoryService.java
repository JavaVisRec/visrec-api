package javax.visrec.spi;

import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.ml.classification.BinaryClassifier;
import javax.visrec.ml.classification.Classifier;
import javax.visrec.ml.classification.ImageClassifier;
import javax.visrec.ml.classification.NeuralNetBinaryClassifier;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Service to provide the correct {@link Classifier} implementation.
 *
 * @author Kevin Berendsen
 * @since 1.0
 */
public final class ClassifierFactoryService {

    private Map<Class<?>, ImageClassifierFactory<?>> imageClassifierFactories;
    private Map<Class<?>, BinaryClassifierFactory<?>> binaryClassifierFactories;

    private static ClassifierFactoryService instance;
    static ClassifierFactoryService getInstance() {
        if (instance == null) {
            instance = new ClassifierFactoryService();
        }
        return instance;
    }

    private ClassifierFactoryService() {
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
        if (imageClassifierFactories == null) {
            imageClassifierFactories = new HashMap<>();
            for (ImageClassifierFactory<?> classifierCreator : ServiceLoader.load(ImageClassifierFactory.class)) {
                imageClassifierFactories.put(classifierCreator.getImageClass(), classifierCreator);
            }
        }

        ImageClassifierFactory<?> creator = imageClassifierFactories.get(block.getImageClass());
        if (creator == null) {
            throw new ClassifierCreationException("Unsupported image class");
        }

        @SuppressWarnings("unchecked")
        ImageClassifierFactory<T> castedCreator = (ImageClassifierFactory<T>) creator;
        return castedCreator.create(block);
    }

    /**
     * Creates a new {@link BinaryClassifier} by providing the {@link NeuralNetBinaryClassifier.BuildingBlock} to tune
     * the implementation's binary classifier.
     *
     * @param block {@link NeuralNetBinaryClassifier.BuildingBlock} is provided to tune the building of the binary classifier.
     * @return {@link BinaryClassifier}
     * @throws ClassifierCreationException if the classifier can not be created due to any reason.
     */
    public <T> BinaryClassifier<T> createNeuralNetBinaryClassifier(NeuralNetBinaryClassifier.BuildingBlock<T> block) throws ClassifierCreationException {
        if (binaryClassifierFactories == null) {
            binaryClassifierFactories = new HashMap<>();
            for (BinaryClassifierFactory<?> classifierCreator : ServiceLoader.load(BinaryClassifierFactory.class)) {
                binaryClassifierFactories.put(classifierCreator.getTargetClass(), classifierCreator);
            }
        }

        BinaryClassifierFactory<?> creator = binaryClassifierFactories.get(block.getTargetClass());
        if (creator == null) {
            throw new ClassifierCreationException("Unsupported target class");
        }

        @SuppressWarnings("unchecked")
        BinaryClassifierFactory<T> castedCreator = (BinaryClassifierFactory<T>) creator;
        return castedCreator.create(block);
    }
}
