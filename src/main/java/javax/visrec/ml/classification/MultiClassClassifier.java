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

import java.util.Map;

/**
 * Machine learning algorithms that provide multi class classification.
 * Multi class classification assigns input object to one of several possible category/class.
 * For example: is it a cat, a dog or a bird?
 * 
 * @param <T> Type of input objects (which are being classified)
 * @param <R> Type of classifier return value - type of object which represent category class.
 */
public interface MultiClassClassifier<T, R> extends Classifier<T, Map<R, Float>>  {
    
}
