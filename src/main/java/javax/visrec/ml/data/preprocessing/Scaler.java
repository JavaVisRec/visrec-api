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
 
package javax.visrec.ml.data.preprocessing;

import javax.visrec.ml.data.DataSet;

/**
 * Interface to perform scaling of a data set.
 * Scaling generally means to change the range of the values, while the shape of the distribution doesn’t change. 
 * Scaling data set features to range [0, 1] or similar is a common practice
 * in order to prepare data for machine learning training and reduce influence of different value ranges among data.
 * 
 * @param <T> Data set class (that implements DataSet interface)
 */
public interface Scaler<T extends DataSet<?>> {
    public void apply(T dataSet);    
}

