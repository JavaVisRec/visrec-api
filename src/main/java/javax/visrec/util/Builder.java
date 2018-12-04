package javax.visrec.util;

import java.util.Properties;
import javax.visrec.ml.data.DataSet;

/**
 * Generic builder interface, that all builders for machine learning algorithms implement.
 *
 * @author Zoran Sevarac
 * @param <T> type of the object to be returned by the builder.
 * @since 1.0
 */
public interface Builder<T> {
    
    /**
     * Builds and returns an object using properties set using available builder methods.
     * 
     * @return 
     */
    public T build();
    
    /**
     * Builds an object using properties from the specified input argument
     * 
     * @param prop
     * @return 
     */
    public T build(Properties prop ); // set everything from configuration properties and then build and return T instance. Make this default method and use keys as method names?
  
}
