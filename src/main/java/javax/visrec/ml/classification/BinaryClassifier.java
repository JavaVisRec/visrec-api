package javax.visrec.ml.classification;

/**
 * Binary classifier classifies input object into one of two possible categories
 * (for example: true/false, yes/no, red/blue, positive/negative, spam/not-spam, fraud/not-fraud).
 * Returns a probability (float value [0..1]) that input object belongs to one positive class.
 */
public interface BinaryClassifier<T> extends Classifier<T, Float> {

}