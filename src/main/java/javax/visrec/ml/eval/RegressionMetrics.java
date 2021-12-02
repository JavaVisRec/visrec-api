/**
 * Visual Recognition API for Java, JSR381
 * Copyright (C) 2020  Zoran Sevarac, Frank Greco
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
 

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
