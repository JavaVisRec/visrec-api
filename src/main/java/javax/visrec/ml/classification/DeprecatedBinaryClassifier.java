package javax.visrec.ml.classification;

/**
 * Binary classifier classifies object into one of two categories (for example: true/false, yes/no, red/blue, spam/not-spam, fraud/not-fraud).
 * Returns a probability that input object belongs to one of two classes.
 * 
 * @author Zoran Sevarac
 * @param <T> type of object to classify
 */
public interface DeprecatedBinaryClassifier<T> extends Classifier<T, Float> {

    
}
