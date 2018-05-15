package javax.visrec.ml.classification;

import java.util.HashMap;
import java.util.Map;

/**
 * EnsambleClassifier is a group of classifiers that provide common
 * classification result, which gives better accuracy then each individual
 * classifier. Usually average or most frequent answer is used as a final result.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class EnsambleClassifier<INPUT_TYPE> implements Classifier<INPUT_TYPE> {

    Map<String, Classifier<INPUT_TYPE>> classifiers = new HashMap<>();

    @Override
    public Map<String, Float> classify(INPUT_TYPE instance) {
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
