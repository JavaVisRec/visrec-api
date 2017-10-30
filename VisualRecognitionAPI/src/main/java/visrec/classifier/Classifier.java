package visrec.classifier;

import java.util.List;
import java.util.Properties;

/**
 * Generic classifier interface, that all classifiers should implement
 * 
 * Based on classifiers from Weka and JML
 * Make this functional interface? Removebuild method? And use builder separetly?
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <INPUT_TYPE>
 * CLASS TYPE can be boolean for binary classifier, enum for small number of predefined classes or string for maximum flexibility and big number of classes
 * 
 * TODO: separate classify and build methods
 *  add builder interface to abstract classes?
 * 
 *     // Interface ClassificationResults<CLASS>
    // http://openimaj.org/apidocs/org/openimaj/experiment/evaluation/classification/ClassificationResult.html
    // http://openimaj.org/apidocs/org/openimaj/experiment/evaluation/classification/BasicClassificationResult.html
 * 
 * @param <CLASS_TYPE>
 */
@FunctionalInterface
public interface Classifier <INPUT_TYPE, CLASS_TYPE> {   // string or boolean, or enum?

    /**
     * Classify specified instance and return classification results
     * 
     * @param instance instance to classify
     * @return instance's class
     */
    public ClassificationResults classify(INPUT_TYPE instance);    
  
           
}
