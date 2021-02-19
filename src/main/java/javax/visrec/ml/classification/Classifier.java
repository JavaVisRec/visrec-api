package javax.visrec.ml.classification;

/**
 * Classifier answers the question what is the category/type of an input object.
 * Each category/type has corresponding label or class name, which can be String, Enum or custom user  defined class.
 * This is a generic classifier interface, that all classifiers should implement, and
 * it provides a method to classify given instances of some class.
 * <p>
 * Implementations should specify input type <T> of instances that are classified,
 * and type of the returned vales <R>.
 * <p>
 * Usually implementations predict category of instances with some probability,
 * and cannot guarantee 100% accuracy.
 *
 * @param <T> type of input objects to classify (eg. User, Product, Transaction, Image, etc.)
 * @param <R> type of classification result (String, Enum, custom class).
 * @see BinaryClassifier
 * @see MultiClassClassifier
 * @see ImageClassifier
 * @since 1.0
 */
@FunctionalInterface
public interface Classifier<T, R> {

    /**
     * Classifies specified input instance of type T and returns classification results of type R.
     *
     * @param input some instance to classify
     * @return classification results for the specified instance
     * @throws ClassificationException if the input could not be classified
     */
    R classify(T input) throws ClassificationException;

}
