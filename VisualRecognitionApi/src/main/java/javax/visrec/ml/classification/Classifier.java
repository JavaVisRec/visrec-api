package javax.visrec.ml.classification;

import java.util.Map;

/**
 * Generic classifier interface, that all classifiers should implement. Provides
 * a method to classify instances of some class. Implementations should specify
 * type of objects (class) that are classified.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 *
 * @param <INPUT_CLASS> type of input instance to classify (eg. User, Product,
 * Event, Transaction, Image, etc.)
 */
@FunctionalInterface
public interface Classifier<INPUT_CLASS> {

    /**
     * Classifies specified instance and returns classification results as
     * map with class names and corresponding classification scores.
     *
     * @param instance some instance to classify
     * @return classification results for the specified instance
     */
    public Map<String, Float> classify(INPUT_CLASS instance);

}
