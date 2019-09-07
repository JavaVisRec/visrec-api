package javax.visrec.util;

/**
 * This interface should be implemented if we want to allow access to underlying ML model
 * @author Zoran
 * @param <M> 
 */
public interface ModelProvider<M> {
    
    M getModel();
   
    // also provide public setModel to allow model injection?
   
}
