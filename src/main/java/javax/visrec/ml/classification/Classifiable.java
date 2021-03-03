package javax.visrec.ml.classification;

/**
 * Classes that implement this interface enable direct classification of Java objects,
 * no need to manually convert to data structures used internally by ML.
 * 
 * This interface simplifies usage and integration of machine learning based classifiers with Java objects.
 * It should be implemented by classes whose objects we want to be able to classify.
 * Instances of classes that implement this interface can be used as examples
 * to build machine learning based classifiers.
 * Typical implementation scenario is to wrap domain specific class and implement this interface.
 * 
 * Classifiable<T, C> can be classfied by the Classifier<T, C> (with the same T and C)
 * 
 * @param <T> Type of input for classifier
 * @param <C> Type of categories/labels (could be anything like enum, String, Integer or user defined class)
 */
public interface Classifiable<T, C> {
    
    /**
     * Returns input for classifier.
     * Implementation of this method should convert attributes of an object for specific classifier.
     * @return 
     */
    T getClassifierInput();
    
    /**
     * Returns target class for classifier.
     * @return 
     */
    C getTargetClass();
}
