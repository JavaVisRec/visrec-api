package javax.visrec.spi;

import javax.visrec.ml.classification.ClassifierBuilder;

/**
 * Service to provide builders.
 *
 * @author Kevin Berendsen
 * @since 1.0
 */
public interface BuilderService {

    /**
     * Creates a new instance of the {@link ClassifierBuilder}
     * @return classifier builder.
     */
    ClassifierBuilder newClassifierBuilder();

}
