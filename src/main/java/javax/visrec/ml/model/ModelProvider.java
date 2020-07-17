package javax.visrec.ml.model;

/**
 * This interface should be implemented by classes which expose access to underlying ML model
 * 
 * @param <T> Type of the underlying ML model
 */
public interface ModelProvider<T> {
    
    T getModel();
   
}
