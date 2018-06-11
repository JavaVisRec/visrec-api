/**  
 *  DeepNetts is pure Java Deep Learning Library with support for Backpropagation 
 *  based learning and image recognition.
 * 
 *  Copyright (C) 2017  Zoran Sevarac <sevarac@gmail.com>
 *
 *  This file is part of DeepNetts.
 *
 *  DeepNetts is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.package deepnetts.core;
 */
    
package javax.visrec.ml.eval;

/**
 * All evaluators implement this interface.
 *  Maybe move to visrec.ml.eval
 * CONSIDER: using more specific model type instead of general model class? Classifier, Regressor?
 *
 * @param <T1> Model class
 * @param <T2> Data set class
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @since 1.0
 */
@FunctionalInterface
public interface Evaluator<T1, T2> {
    
    /**
     * Evaluate model with specified data set.
     * Return Map with performance metrics and values?
     * Map<String, PerformanceMeasure> ili Map<Object, PerformanceMeasure>
     *
     * @param model A model to evaluate
     * @param testSet Data to use for evaluation
     * @return performance measures of a model for the specified test set
     */    // evaluatePerformance       testDataSet
    PerformanceMeasure evaluatePerformance(T1 model, T2 testSet); // kako ce da vrati rezultate testiranja - napraviti neku klasu za to?
    
}
