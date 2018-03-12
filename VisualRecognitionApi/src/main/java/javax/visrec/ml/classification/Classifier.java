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
public interface Classifier <INPUT_TYPE> {

    /**
     * Classify specified instance and return map with possible classes/categories
     * and corresponding confidence scores (most often probability).
     * 
     * @param instance instance to classify
     * @return map of category/class names and corresponding confidence score / probability
     */
    public Map<String, Float> classify(INPUT_TYPE instance);
           
}