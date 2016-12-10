package visrec.classifier;

/**
 * Generic classifier interface
 * 
 * Based on classifiers from Weka and JML
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface Classifier <I, C> {
        
    void buildClassifier(Map<I, C> data);


    // Classify the instance according to this classifier.
    C classify(I instance);


    // Generate the membership distribution for this instance using this classifier.
    Map<C, Double> classDistribution (I instance);    
}
