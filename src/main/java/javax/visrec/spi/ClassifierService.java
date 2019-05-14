package javax.visrec.spi;

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
     */
    ImageClassifier createImageClassifier(ImageClassifier.BuildingBlock block);
}
