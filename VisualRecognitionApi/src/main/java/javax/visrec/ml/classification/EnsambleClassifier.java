package javax.visrec.ml.classification;

import java.util.HashMap;
import java.util.Map;

/**
 * EnsambleClassifier represents a group of classifiers that provide common
 * (most frequent) answer which gives better accuracy then each individual
 * classifier.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class EnsambleClassifier<INPUT_TYPE> implements Classifier<INPUT_TYPE> {

    Map<String, Classifier<INPUT_TYPE>> classifiers = new HashMap<>();

    @Override
    public Map<String, Float> classify(INPUT_TYPE instance) {
        classifiers.values().stream() // or parallelStream
                .forEach(c -> c.classify(instance));
        //.collect();
        // return merged classification result of all classifiers  - mean or most frequent?
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
