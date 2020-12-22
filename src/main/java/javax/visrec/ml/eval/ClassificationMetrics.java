package javax.visrec.ml.eval;

/**
 * Commonly used metrics to estimate how good machine learning model solves some classification task.
 */
public class ClassificationMetrics extends EvaluationMetrics {

    /**
     * The percent of correct predictions of a classifier in total.
     * Tells how often the classifier is correct for some given test set.
     * Note that accuracy can be a misleading metric for imbalanced data sets.
     * 
     * Formula: Accuracy = (TP+TN) / (TP+TN+FP+FN)
     * 
     * @return classification accuracy metric
     */
    public double getAccuracy() {
        return get(ACCURACY);
    }

    /**
     * The percent of correct positive predictions.
     * This metrics answers the question: when classifier gives positive prediction, how often is it correct?
     * Formula: Precision = TP/(TP+FP)
     * 
     * @return classification precision metric
     */
    public double getPrecision() {
        return get(PRECISION);
    }

    /**
     * The percent of the positive examples that were not recognized by the classifier.
     * This metric answers the question: when it is actually positive class, how often does it give positive prediction
     * Formula: Recall = TP / (TP+FN)
     * 
     * @return classification recall
     */
    public double getRecall() {
        return get(RECALL);
    }

    /**
     * The mean/balance of the recall and precision metrics.
     * 
     * @return classification f score
     */
    public double getF1score() {
        return get(F1SCORE);
    }
    
}