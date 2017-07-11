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
 */
public interface Classifier <INPUT_TYPE, CLASS_TYPE> {   // string or boolean, or enum?

    /**
     * Classify specified instance and return corresponding class
     * 
     * @param instance instance to classify
     * @return instane's class
     */
    public  List<ClassificationResult<CLASS_TYPE>> classify(INPUT_TYPE instance);    
    // Interface ClassificationResult<CLASS>
    // http://openimaj.org/apidocs/org/openimaj/experiment/evaluation/classification/ClassificationResult.html
    // http://openimaj.org/apidocs/org/openimaj/experiment/evaluation/classification/BasicClassificationResult.html

    
//    
//    /**
//     * Builds classifier with specified configuration (properties)
//     * 
//     * @param prop settings to build specific classifier
//     */
    public void build(Properties prop);

}
