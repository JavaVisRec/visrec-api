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
