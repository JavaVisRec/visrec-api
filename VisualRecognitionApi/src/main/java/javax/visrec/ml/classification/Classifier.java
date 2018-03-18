package javax.visrec.ml.classification;

import java.util.Map;

/**
 * Generic classifier interface, that all classifiers should implement.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 *
 * @param <INPUT_TYPE> type of input instance
 *
 */
@FunctionalInterface
public interface Classifier<INPUT_TYPE> {

    /**
     * Classify specified instance and return classification results return a
     * Map (class, confidence? no need for the additional classes.. whats their
     * purporse?)
     *
     * @param instance some instance to classify
     * @return classification results for the specified instance
     */
    public Map<String, Float> classify(INPUT_TYPE instance);

}
