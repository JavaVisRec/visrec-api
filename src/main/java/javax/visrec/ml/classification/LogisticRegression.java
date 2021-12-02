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
 
package javax.visrec.ml.classification;

import java.util.Objects;
import javax.visrec.ml.model.ModelProvider;

/**
 * This class performs basic binary classification - mapping of specified input to true/false with probability 
 * using logistic regression algorithm. 
 * Subclasses should use specific logistic regression implementation to provide that functionality.
 *
 * @param <MODEL_CLASS> Implementation class of underlying machine learning model
 */
public abstract class LogisticRegression<MODEL_CLASS> implements BinaryClassifier<float[]>, ModelProvider<MODEL_CLASS> {

    private MODEL_CLASS model;

    @Override
    public MODEL_CLASS getModel() {
        return model;
    }
    
    protected final void setModel(MODEL_CLASS model) {
        this.model = Objects.requireNonNull(model, "Model cannot be null!");
    }    

}
