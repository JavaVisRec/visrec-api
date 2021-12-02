/**
 * Visual Recognition API for Java, JSR381
 * Copyright (C) 2020  Zoran Sevarac, Frank Greco
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
 
package javax.visrec.ml.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Generic model builder interface, that all builders for machine learning algorithms implement.
 *
 * @param <T> type of the object to be returned by the builder.
 * @since 1.0
 */
public interface ModelBuilder<T> {

    /**
     * Builds and returns an object using properties set using available builder methods.
     *
     * @return object specified by the builder to build
     * @throws javax.visrec.ml.model.ModelCreationException
     */
    T build() throws ModelCreationException;

    /**
     * Builds an object using properties from the specified input argument
     *
     * @param configuration properties for the builder, a map of key, value pairs.
     * @return object specified by the builder to build
     * @throws javax.visrec.ml.model.ModelCreationException
     */
    default T build(Map<String, Object> configuration) throws ModelCreationException {
        ModelBuilder<T> thizz = this;
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (!method.getName().equals("build") && method.getParameterCount() == 1
                    && configuration.containsKey(method.getName())) {
                try {
                    Object obj = method.invoke(thizz, configuration.get(method.getName()));
                    if (thizz.getClass().isInstance(obj)) {
                        thizz = (ModelBuilder<T>) thizz.getClass().cast(obj);
                    }
                } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                    throw new InvalidConfigurationException("Couldn't invoke '" + method.getName() + "'", e);
                }
            }
        }
        return thizz.build();
    }

}
