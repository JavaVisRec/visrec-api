package javax.visrec.internal;

import javax.visrec.NoSuchGenericInterfaceException;
import javax.visrec.NoSuchGenericTypeArgumentException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A utility class to safely cast objects of classes which are using generic types. The generic types are erased and
 * not known on runtime. Using reflection on the class definition, it is possible to determine which classes are used
 * as generic types.
 *
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
final class SafeCastUtil {

    /**
     * Private constructor to prevent class instantiation.
     */
    private SafeCastUtil() {
        // Empty.
    }

    /**
     * Checks whether the object, interface and arguments can be casted safely on runtime due to generic class erasure.
     * @param obj {@code Object} which implements {@code genericIntf}, may not be null
     * @param genericIntf The generic interface to validate the arguments on, may not be null
     * @param arguments Generic type arguments of {@code genericIntf}, may not be null and should at least
     *                  contain one argument.
     * @throws IllegalArgumentException If {@code genericIntf} is not an interface or if the generic type arguments are
     * empty.
     * @throws NoSuchGenericTypeArgumentException if the generic type argument can not be found on the {@code object} and therefore
     * can not be casted safely.
     */
    static void canSafelyCast(final Object obj, final Class<?> genericIntf, final Class<?>... arguments) {
        Objects.requireNonNull(obj);
        Objects.requireNonNull(genericIntf);
        if (!genericIntf.isInterface()) {
            throw new IllegalArgumentException(String.format("genericIntf is not an interface: %s", genericIntf.getName()));
        }
        if (arguments == null || arguments.length == 0) {
            throw new IllegalArgumentException("Generic types argument is null or empty");
        }

        final List<Class<?>> foundGenericTypes = findGenericTypesOnClass(obj, genericIntf);
        final List<Class<?>> matchedTypes = new ArrayList<>();
        for (Class<?> cls : arguments) {
            for (int i = 0; i < foundGenericTypes.size(); i++) {
                if (cls.equals(foundGenericTypes.get(i))) {
                    matchedTypes.add(cls);
                    foundGenericTypes.remove(i);
                    break;
                }
            }
        }

        if (matchedTypes.size() != arguments.length) {
            final String[] missingTypes = foundGenericTypes.stream()
                    .filter(e -> !matchedTypes.contains(e))
                    .map(Class::toString)
                    .toArray(String[]::new);
            final String missingTypesMsg = String.join(",", missingTypes);
            throw new NoSuchGenericTypeArgumentException(String.format("Missing %d argument(s): %s",
                    Math.abs(arguments.length - matchedTypes.size()),
                    missingTypesMsg));
        }
    }

    /**
     * Find generic types arguments on a class.
     * @param obj {@code Object} which implements {@code genericIntf}, may not be null
     * @param genericIntf The generic interface to validate the arguments on, may not be null
     * @return List of classes with possible generic type arguments.
     * @throws NoSuchGenericInterfaceException If the interface can not be found on the object.
     */
    static List<Class<?>> findGenericTypesOnClass(final Object obj, final Class<?> genericIntf) {
        final Class<?> cls = obj.getClass();
        if (Objects.isNull(cls.getGenericInterfaces())) {
            return null;
        }

        final List<Class<?>> foundTypes = new ArrayList<>();
        final Type[] genericInterfaces = cls.getGenericInterfaces();
        ParameterizedType foundGenericIntf = null;
        for (Type type : genericInterfaces) {
            final ParameterizedType castedType = (ParameterizedType) type;
            if (genericIntf.equals(castedType.getRawType())) {
                foundGenericIntf = castedType;
                break;
            }
        }

        if (foundGenericIntf == null) {
            throw new NoSuchGenericInterfaceException(String.format("Couldn't find the generic interface (%s) on %s",
                    genericIntf, obj.getClass().toString()));
        }

        if (foundGenericIntf.getActualTypeArguments().length > 0) {
            for (Type typeArg : foundGenericIntf.getActualTypeArguments()) {
                if (typeArg instanceof Class<?>) {
                    foundTypes.add((Class<?>) typeArg);
                }
            }
        }
        return foundTypes;
    }
}
