package visrec.util;

import java.util.Properties;

/**
 * Generic builder interface 
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface Builder<T> {
    public T build(Properties prop ); // set everything from configuration properties and then build and return T instance 
    // public T build(); // regural builder method
}
