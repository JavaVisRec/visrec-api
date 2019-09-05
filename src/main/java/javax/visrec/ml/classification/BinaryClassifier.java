package javax.visrec.ml.classification;

/**
 * Binary classifier classifies object into one of two categories (for example: true/false, yes/no, red/blue, spam/not-spam, fraud/not-fraud)
 * 
 * @author Zoran
 * @param <T> type of object to classify
 */
public interface BinaryClassifier<T> extends Classifier<T, Float> {
    
    
}
