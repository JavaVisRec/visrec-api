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

import javax.visrec.ml.model.ModelProvider;

/**
 * Skeleton base class for implementations of multi class classifiers.
 * 
 * @param <MODEL_CLASS> class of machine learning model back-end
 * @param <T> type of classifier's input
 * @param <R> type of classifier's results/class
 */
public abstract class AbstractMultiClassClassifier<MODEL_CLASS, T, R> implements MultiClassClassifier<T, R>, ModelProvider<MODEL_CLASS> {

    private MODEL_CLASS model;

    @Override
    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }

}
