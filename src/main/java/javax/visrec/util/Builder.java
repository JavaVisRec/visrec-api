package javax.visrec.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Generic builder interface, that all builders for machine learning algorithms implement.
 *
 * @author Zoran Sevarac
 * @author Kevin Berendsen
 * @param <T> type of the object to be returned by the builder.
 * @since 1.0
 */
public interface Builder<T> {

    /**
     * Builds and returns an object using properties set using available builder methods.
     *
     * @return object specified by the builder to build
     */
    T build();

    /**
     * Builds an object using properties from the specified input argument
     *
     * @param configuration properties for the builder, a map of key, value pairs.
     * @return object specified by the builder to build
     */
    default T build(Map<String, Object> configuration) {
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (!method.getName().equals("build") && method.getParameterCount() == 1
                    && configuration.containsKey(method.getName())) {
                try {
                    method.invoke(this, configuration.get(method.getName()));
                } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                    throw new InvalidBuilderConfigurationException("Couldn't invoke '" + method.getName() + "'", e);
                }
            }
        }
        return build();
    }

}