package javax.visrec.ml.classification;

import java.util.Map;

/**
 * Machine learning algorithms that provide multi class classification.
 * Multi class classification assigns input object to one of several possible category/class.
 * For example: is it a cat, a dog or a bird?
 * 
 * @author Zoran Sevarac
 * @param <T> Type of input objects (which are being classified)
 * @param <R> Type of classifier return value - type of object which represent category class.
 */
public interface MultiClassClassifier<T, R> extends Classifier<T, Map<R, Float>>  {
    
}
