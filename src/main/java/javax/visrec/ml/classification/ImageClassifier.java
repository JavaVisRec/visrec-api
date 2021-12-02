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


import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

/**
 * Classifier interface specialized in image classification
 *
 * @param <IMAGE_CLASS> type of input objects to classify (eg. User, Product, Transaction, Image, etc.)
 * @since 1.0
 */
public interface ImageClassifier<IMAGE_CLASS> extends Classifier<IMAGE_CLASS, Map<String, Float>> {

    /**
     * Classify the input and get a map of classification results as output
     *
     * @param input {@link Path} to use as input
     * @return {@code Map} with key as classification label and with value as accuracy percentage of likelihood
     * @throws ClassificationException if the file couldn't be found or classified
     */
    Map<String, Float> classify(Path input) throws ClassificationException;

    /**
     * Classify the input and get a map of classification results as output
     *
     * @param input {@link InputStream} to use as input
     * @return {@code Map} with key as classification label and with value as accuracy percentage of likelihood
     * @throws ClassificationException if input couldn't be classified
     */
    Map<String, Float> classify(InputStream input) throws ClassificationException;

}
