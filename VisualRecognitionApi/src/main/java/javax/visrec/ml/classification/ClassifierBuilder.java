package javax.visrec.ml.classification;

import javax.visrec.spi.ServiceProvider;

/**
 * Builder to create @{link Classifier}
 *
 * @author Kevin Berendsen
 * @since 1.0
 */
public abstract class ClassifierBuilder {

    protected ClassifierBuilder() {
        // Prevent instantiation outside of subclasses.
    }

    /**
     * Creates a new instance of the builder.
     * @return the builder
     */
    public static ClassifierBuilder newBuilder() {
        return ServiceProvider.current().getBuilderService().newClassifierBuilder();
    }

    /**
     * Set the trained model to be used during the image classification.
     *
     * TODO Add the proper class to the method signature.
     *
     * @param trainedModel the object of the trained model.
     * @return current builder instance
     */
    public abstract ClassifierBuilder trainedModel(Object trainedModel);

    /**
     * Create the {@link Classifier} that is able to use {@code cls} as input/source type.
     *
     * @param cls {@link Class} object of the incoming input/source.
     * @param <T> the class used during the build method and parameterized type of the {@link Classifier}
     * @return {@link Classifier} object.
     */
    public abstract <T> Classifier<T> buildWithSourceType(Class<T> cls);
}
