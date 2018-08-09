package javax.visrec.util;

import java.util.Properties;

/**
 * Generic builder interface.
 *
 * @author Zoran Sevarac
 * @param <T> type of the object to be returned by the builder.
 * @since 1.0
 */
public interface Builder<T> {

    T build(Properties prop ); // set everything from configuration properties and then build and return T instance

    // public T build(); // regural builder method
}
