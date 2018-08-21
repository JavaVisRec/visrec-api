package javax.visrec.spi;

import javax.visrec.ml.classification.Classifier;

/**
 * Service to provide the correct {@link Classifier} implementation.
 *
 * @author Kevin Berendsen
 * @since 1.0
 */
public interface ClassifierService {

    /**
     * Get the {@link Classifier} which is able to classify images by the {@code sourceType} object.
     *
     * @param sourceType {@link Class} object of the source type.
     * @param <T> the class which is able to be handled by the {@link Classifier} and given as {@link Class} object.
     * @return {@link Classifier} which is able to classify images of {@code T}
     */
    <T> Classifier<T> getBySource(Class<T> sourceType);
}
