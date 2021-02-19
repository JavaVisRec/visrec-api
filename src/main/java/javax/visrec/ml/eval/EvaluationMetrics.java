/**
 *  DeepNetts is pure Java Deep Learning Library with support for Backpropagation
 *  based learning and image recognition.
 *
 *  Copyright (C) 2017  Zoran Sevarac <sevarac@gmail.com>
 *
 * This file is part of DeepNetts.
 *
 * DeepNetts is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.package
 * deepnetts.core;
 */

package javax.visrec.ml.eval;

import java.util.HashMap;

/**
 * Wrapper for constants and values for classifier and regressor evaluation metrics.
 * 
 */
public class EvaluationMetrics {

    /**
    * Mean value of sum of squared errors.
    * Errors are squared in order to better explain variability, and take into account positive and negative errors.
    * Regression metrics
    */
    public final static String MEAN_ABSOLUTE_ERROR      = "MeanAbsoluteError";
    public final static String MEAN_SQUARED_ERROR       = "MeanSquaredError";
    public final static String ROOT_MEAN_SQUARED_ERROR  = "RootMeanSquaredError";
    public final static String RESIDUAL_SQUARE_SUM      = "ResidualSquareSum"; // RSS

    /**
     * Estimation of standard deviation of prediction errors for some given data set.
     * Smaller is better.
     */
    public final static String RESIDUAL_STANDARD_ERROR = "ResidualStandardError";

    /**
     * Percent of variation explained by the regression model.
     * 1 is good , 0 is bad, bigger is better.
     */
    public final static String R_SQUARED = "RSquared";

    /**
     * Is there a relationship between inputs and predictions(outputs) at all.
     * If there is a relationship, this value is greater then 1. 
     * When there is now relationship, this value is around 1.
     */
    public final static String F_STAT = "FStatistics";

    // Classification Metrics
    public final static String ACCURACY         = "Accuracy";
    public final static String PRECISION        = "Precision";
    public final static String RECALL           = "Recall";
    public final static String F1SCORE          = "F1Score";

    // Hold values of relevant evaluation metrics
    private final HashMap<String, Float> values = new HashMap();
    
    private final static HashMap<String, String> description = new HashMap();
    static {
        description.put(ACCURACY, "How often is classifier correct in total");
        description.put(PRECISION, "How often is classifier correct when it gives positive prediction");
        description.put(RECALL, "When it is actually positive class, how often does it give positive prediction");
        description.put(F1SCORE, "Harmonic average (balance) of precision and recall");
    }
    

    public float get(String key) {
        return values.get(key);
    }

    public void set(String key, float value) {
        values.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        values.entrySet().stream().forEach((e) ->  { sb.append(e.getKey()).append(": ")
                                                     .append(e.getValue());
                                                     if (description.get(e.getKey())!=null)   
                                                        sb.append(" (")
                                                          .append(description.get(e.getKey())) 
                                                          .append(")");
                                                     sb.append(System.lineSeparator());
                                                    });
        return sb.toString();
    }

}
