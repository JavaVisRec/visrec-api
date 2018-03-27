package javax.visrec.ml.classification;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class EnsambleClassifier<INPUT_TYPE, CLASS_TYPE> implements Classifier<INPUT_TYPE> {

    Map<String, Classifier<INPUT_TYPE>> classifiers = new HashMap<>();

    @Override
    public Map<String, Float> classify(INPUT_TYPE instance) {
        classifiers.values().stream() // or parallelStream
                .forEach(c -> c.classify(instance));
        //.collect();
        // return merged classification result of all classifiers  - mean or median
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
