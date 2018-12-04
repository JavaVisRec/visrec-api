package javax.visrec.ml.classification;

import java.util.Map;

/**
 * Generic classifier interface, that all classifiers should implement. Provides
 * a method to classify instances of some class. Implementations should specify
 * type of objects (class) that are classified.
 *
 * @author Zoran Sevarac
 * @param <T> type of input instance to classify (eg. User, Product,
 * Event, Transaction, Image, etc.)
 * @param <R> type of classification result map eg. String is commonly used , but Enum as well
 * @since 1.0
 */
@FunctionalInterface
public interface Classifier<T, R> {

    /**
     * Classifies specified instance and returns classification results as
     * map with class names and corresponding classification scores.
     *
     * @param input some instance to classify
     * @return classification results for the specified instance
     */
    public Map<R, Float> classify(T input);
        

}
