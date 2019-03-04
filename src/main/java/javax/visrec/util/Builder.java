package javax.visrec.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Generic builder interface, that all builders for machine learning algorithms implement.
 *
 * @author Zoran Sevarac
 * @param <T> type of the object to be returned by the builder.
 * @since 1.0
 */
public interface Builder<T> {

    /**
     * Returns the default configuration for the builder. By default it is returning an empty {@code HashMap}
     * @return {@code Map} of {@code String} as key and {@code Object} as value.
     */
    default Map<String, Object> defaultConfiguration() {
        return new HashMap<>();
    }

    /**
     * Builds and returns an object using properties set using available builder methods.
     *
     * @return object specified by the builder to build
     */
    default T build() {
        return build(defaultConfiguration());
    }

    /**
     * Builds an object using properties from the specified input argument
     *
     * @param configuration properties for the builder, a map of key, value pairs.
     * @return object specified by the builder to build
     */
    T build(Map<String, Object> configuration);

}
