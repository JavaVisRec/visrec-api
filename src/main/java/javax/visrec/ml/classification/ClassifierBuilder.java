package javax.visrec.ml.classification;

import javax.visrec.spi.ServiceProvider;

/**
 * Builder to create @{link Classifier}
 *
 * @author Kevin Berendsen
 * @since 1.0
 * @Deprecated for removal
 */
@Deprecated
public abstract class ClassifierBuilder {

    protected ClassifierBuilder() {
        // Prevent instantiation outside of subclasses.
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
     * Create the {@link Classifier} that is able to use {@code sourceClss} as input/source type and
     * {@code returnCls} as return type of the {@link Classifier}
     *
     * @param sourceCls {@link Class} object of the incoming input/source.
     * @param returnCls {@link Class} object of the return type of the {@link Classifier}
     * @param <T> source type class of the {@link Classifier}
     * @param <R> return type class of the {@link Classifier}
     * @return {@link Classifier} object.
     */
    public abstract <T, R> Classifier<T, R> buildWithSourceType(final Class<T> sourceCls, final Class<R> returnCls);

    /**
     * Create the {@link Classifier} that is able to use {@code sourceClss} as input/source type and
     * {@link String} as default return type of the {@link Classifier}
     *
     * @param sourceCls {@link Class} object of the incoming input/source.
     * @param <T> source type class of the {@link Classifier}
     * @return {@link Classifier} object.
     */
    public final <T> Classifier<T, String> buildWithSourceType(final Class<T> sourceCls) {
        return buildWithSourceType(sourceCls, String.class);
    }
}
