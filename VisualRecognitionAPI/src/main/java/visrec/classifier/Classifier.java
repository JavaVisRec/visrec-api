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
public interface Classifier <INSTANCE_TYPE, RESULT_TYPE> {

    /**
     * Classify specified instance and return corresponding class
     * 
     * @param instance instance to classify
     * @return instane's class
     */
    RESULT_TYPE classify(INSTANCE_TYPE instance);    
    
    
    /**
     * Builds classifier with specified settings
     * 
     * @param prop vatious settings to build specific classifier
     */
    void build(Properties prop);

}
