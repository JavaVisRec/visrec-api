package visrec.classifier;

import java.util.Properties;

/**
 * Generic classifier interface, that all classifiers should implement
 * 
 * Based on classifiers from Weka and JML
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <INSTANCE_TYPE>
 * @param <RESULT_TYPE>
 */
public interface Classifier <INSTANCE_TYPE, RESULT_TYPE> {

    /**
     * Classify specified instance and return corresponding class
     * 
     * @param instance instance to classify
     * @return instane's class
     */
    public RESULT_TYPE classify(INSTANCE_TYPE instance);    
    
    
    /**
     * Builds classifier with specified configuration (properties)
     * 
     * @param prop settings to build specific classifier
     */
    public void build(Properties prop);

}
