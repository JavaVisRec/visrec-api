package visrec.classifier;

import java.util.Map;
import java.util.Properties;

/**
 * Generic classifier interface, that all classifiers should implement
 * 
 * Based on classifiers from Weka and JML
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface Classifier <I, C> {
        
    void buildClassifier(Properties data); // how to specify specific classifier options in this feneric builde- ? add List<ClassifierOpetion> ? ClassifierOption<Type> (nmae, value)


    // Classify the instance according to this classifier.
    C classify(I instance);


    // Generate the membership distribution for this instance using this classifier. this is maybe not needed
//    Map<C, Double> classDistribution (I instance);    
}
