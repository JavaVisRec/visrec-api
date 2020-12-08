package javax.visrec.ml.eval;

/**
 * Commonly used metrics to estimate how good machine learning model solves some classification task.
 * 
 */
public class ClassificationMetrics extends EvaluationMetrics {

    public double getAccuracy() {
        return get(ACCURACY);
    }

    public double getPrecision() {
        return get(PRECISION);
    }

    public double getRecall() {
        return get(RECALL);
    }

    public double getF1score() {
        return get(F1SCORE);
    }
    
}