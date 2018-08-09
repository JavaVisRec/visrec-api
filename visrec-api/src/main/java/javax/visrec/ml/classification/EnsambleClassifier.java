package javax.visrec.ml.classification;

import java.util.HashMap;
import java.util.Map;

/**
 * EnsambleClassifier is a group of classifiers that provide common
 * classification result, which gives better accuracy then each individual
 * classifier. Usually average or most frequent answer is used as a final result.
 *
 * @author Zoran Sevarac
 * @param <T> The input type which is to be classified.
 * @since 1.0
 */
public final class EnsambleClassifier<T> implements Classifier<T> {

    Map<String, Classifier<T>> classifiers = new HashMap<>();

    @Override
    public Map<String, Float> classify(T instance) {
        classifiers.values().stream() // or parallelStream
                .forEach(c -> c.classify(instance));
        //.collect(); // get average scores? This method can be overriden, provide default impl here
        // return merged classification result of all classifiers  - mean or most frequent?
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // or just provide method for adding swith some intrenal id?
    public void addClassifier(String classifierId, Classifier classifier) {
        classifiers.put(classifierId, classifier);
    }

    public Classifier getClassifier(String classiferId) {
        return classifiers.get(classiferId);
    }

    public void remove(String classifierId) {
        classifiers.remove(classifierId);
    }

}
