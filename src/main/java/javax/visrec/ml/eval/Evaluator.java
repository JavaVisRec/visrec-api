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
 * Evaluation method for specified types of machine learning model and data set.
 * All evaluators implement this interface.
 *
 * @param <MODEL_CLASS> Model class
 * @param <DATASET_CLASS> Data set class
 *
 * @since 1.0
 */
@FunctionalInterface
public interface Evaluator<MODEL_CLASS, DATASET_CLASS> {
    
    /**
     * Evaluate a model with specified test set.
     *
     * @param model A model to evaluate
     * @param testSet Data to use for evaluation
     * @return evaluation metrics for the model for the specified test set
     */ 
    public EvaluationMetrics evaluate(MODEL_CLASS model, DATASET_CLASS testSet);
    
}
