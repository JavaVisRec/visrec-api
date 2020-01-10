package javax.visrec.spi;

import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.ml.classification.BinaryClassifier;
import javax.visrec.ml.classification.Classifier;
import javax.visrec.ml.classification.ImageClassifier;

/**
 * Service to provide the correct {@link Classifier} implementation.
 *
 * @author Kevin Berendsen
 * @since 1.0
 */
public interface ClassifierService {

    /**
     * Creates a new {@link ImageClassifier} by providing the {@link ImageClassifier.BuildingBlock} to tune
     * the implementation's image classifier.
     * @param block {@link ImageClassifier.BuildingBlock} is provided to tune the building of the image classifier.
     * @return {@link ImageClassifier}
     * @throws ClassifierCreationException if the classifier can not be created due to any reason.
     */
    <T> ImageClassifier<T> createImageClassifier(ImageClassifier.BuildingBlock<T> block) throws ClassifierCreationException;

    /**
     * Creates a new {@link BinaryClassifier} by providing the {@link BinaryClassifier.BuildingBlock} to tune
     * the implementation's binary classifier.
     * @param block {@link BinaryClassifier.BuildingBlock} is provided to tune the building of the binary classifier.
     * @return {@link BinaryClassifier}
     * @throws ClassifierCreationException if the classifier can not be created due to any reason.
     */
    BinaryClassifier createBinaryClassifier(BinaryClassifier.BuildingBlock block) throws ClassifierCreationException;
}
