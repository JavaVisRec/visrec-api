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

/**
 * Generic classifier interface, that all classifiers should implement, and
 * it provides a method to classify given instances of some class.
 * Each category/type has corresponding label or class, which can be String, Enum or custom user defined class.
 * Machine learning based classifier can learn from examples how to determine a 
 * category of an input object with high degree of confidence.
 *
 * <p>
 * Implementations should specify input type <T> of instances that are classified,
 * and type of the returned vales <R>.
 * <p>
 * Usually implementations predict category of instances with some probability,
 * and cannot guarantee 100% accuracy.
 *
 * @param <T> type of input objects to classify (eg. User, Product, Transaction, Image, etc.)
 * @param <R> type of classification result (String, Enum, custom class).
 * @see BinaryClassifier
 * @see MultiClassClassifier
 * @see ImageClassifier
 * @see Classifiable
 * @since 1.0
 */
@FunctionalInterface
public interface Classifier<T, R> {

    /**
     * Classifies specified input instance of type T and returns classification results of type R.
     *
     * @param input some instance to classify
     * @return classification results for the specified instance
     * @throws ClassificationException if the input could not be classified
     */
    R classify(T input) throws ClassificationException;

}
