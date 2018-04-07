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

import java.util.Map;

/**
 * All evaluators implement this interface.
 *  Maybe move to visrec.ml.eval
 * CONSIDER: using more specific model type instead of general model class? Classifier, Regressor?
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <MODEL_CLASS>
 * @param <DATA_CLASS>
 */
@FunctionalInterface
public interface Evaluator<MODEL_CLASS, DATASET_CLASS> {
    
    /**
     * Evaluate model with specified data set.
     * Return Map with performance metrics and values?
     * Map<String, PerformanceMeasure> ili Map<Object, PerformanceMeasure>
     *
     * @param model A model to evaluate
     * @param testSet Data to use for evaluation
     * @return performance measures of a model for the specified test set
     */    // evaluatePerformance       testDataSet
    public PerformanceMeasure evaluatePerformance(MODEL_CLASS model, DATASET_CLASS testSet); // kako ce da vrati rezultate testiranja - napraviti neku klasu za to?
    
}
