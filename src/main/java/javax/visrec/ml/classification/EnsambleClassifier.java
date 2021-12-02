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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EnsambleClassifier is a group of classifiers that provide common
 * classification result, which gives better accuracy then each individual
 * classifier. Usually average or most frequent answer is used as a final result.
 *
 * @param <T> The input type which is to be classified.
 * @param <R> Return type of the classifier.
 * @since 1.0
 */
public final class EnsambleClassifier<T, R> implements Classifier<T, R> {

    private final Map<String, Classifier<T, R>> classifiers = new HashMap<>();

    @Override
    public R classify(T input) {
        List<R> results = new ArrayList<>();
        Map<String, Integer> freqCount = new HashMap<>();
        int maxFreq = 0;
        R maxClass = null;
        for (Map.Entry<String, Classifier<T, R>> classifier : classifiers.entrySet()) {
            R result = classifier.getValue().classify(input);
            results.add(result);
            // what if it is a binary classifier ? it should return class name with corresponding probability
            // if (instanceof BinaryClassifier) deal with binary classifiers using if statement
            if (freqCount.containsKey(result)) {
                freqCount.put(result.toString(), freqCount.get(result.toString())+1);
            } else {
                freqCount.put(result.toString(), 1);
            }
            
            if (freqCount.get(result.toString()) > maxFreq) {
                maxFreq = freqCount.get(result.toString());
                maxClass = result;
            }
        }             
        
        return maxClass;       
    }

    public EnsambleClassifier addClassifier(String classifierId, Classifier<T, R> classifier) {
        classifiers.put(classifierId, classifier);
        return this;
    }

    public Classifier getClassifier(String classiferId) {
        return classifiers.get(classiferId);
    }

    public void remove(String classifierId) {
        classifiers.remove(classifierId);
    }

}
