package javax.visrec.ml.classification;

/**
 * Generic classifier interface, that all classifiers should implement. Provides
 * a method to classify instances of some class. Implementations should specify
 * type of objects (class) that are classified.
 *
 * @author Zoran Sevarac
 * @param <T> type of input objects to classify (eg. User, Product, Transaction, Image, etc.)
 * @param <R> type of classification result.
 * @see BinaryClassifier
 * @see MultiClassClassifier
 * @since 1.0
 */
@FunctionalInterface
public interface Classifier<T, R> {

    /**
     * Classifies specified instance of type T and returns classification results of type R.
     *
     * @param input some instance to classify
     * @return classification results for the specified instance
     */
    R classify(T input);
       
}
