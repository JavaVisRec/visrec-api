package javax.visrec.ml.eval;

/**
 * Commonly used metrics to estimate how good machine learning model solves some regression task.
 * This class is typed wrapper for EvaluationMetrics which uses map to store metrics, 
 * and allows storing and accessing of custom metrics.
 */
public class RegressionMetrics extends EvaluationMetrics {
    
    /**
     * Portion of variation explained. 
     * How much better is this than the average.
     * @return 
     */
    public double getRSquared() {
        return get(R_SQUARED);
    }
    
    public double getResidualSquareSum() {
        return get(RESIDUAL_SQUARE_SUM);
    }    

    public double getFstat() {
        return get(F_STAT);
    }
       
    public double getMeanSquaredError() {
        return get(MEAN_SQUARED_ERROR);
    }

    public double getResidualStandardError() {
        return get(RESIDUAL_STANDARD_ERROR);
    }
            
}
