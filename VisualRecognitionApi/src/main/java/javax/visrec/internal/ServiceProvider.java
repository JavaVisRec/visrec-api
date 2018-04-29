package javax.visrec.internal;

import java.util.*;

/**
 * The ServiceProvider searches for implementation of a specification interface.
 *
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class ServiceProvider {

    /** Map containing all the used {@link ServiceLoader}s. */
    private final Map<Class<?>, ServiceLoader<?>> serviceLoaders = new HashMap<>();

    /** Map containing all cached services. The key if the specification interface and the value is a list of implementations */
    private final Map<Class<?>, List<?>> cachedImplementations = new LinkedHashMap<>();

    /** Lock object to get and create {@link ServiceLoader}s */
    private final Object SERVICE_LOADER_LOCK = new Object();

    /** Lock object to populate the implementations in the cached map which are given by the {@link ServiceLoader} */
    private final Object POPULATE_IMPL_LOCK = new Object();

    /**
     * Private constructor to prevent class instantiation outside this class.
     */
    private ServiceProvider() {
        // Empty.
    }

    /**
     * Get the singleton instance of {@link ServiceProvider}
     * @return {@link ServiceProvider}
     */
    public static ServiceProvider getInstance() {
        return Singleton.INSTANCE;
    }


    /**
     * Find the implementations of an interface.
     * @param cls {@link Class} to find the implementations of. May not be null and must be an interface.
     * @param <T> Interface type of {@code cls}
     * @return List of implementations
     * @throws IllegalArgumentException If {@code cls} is not an interface.
     */
    public <T> List<T> findImplementations(Class<T> cls) {
        Objects.requireNonNull(cls);
        if (!cls.isInterface()) {
            throw new IllegalArgumentException(String.format("Given Class object is not an interface: %s", cls.toString()));
        }

        if (cachedImplementations.containsKey(cls)) {
            @SuppressWarnings("unchecked")
            final List<T> impls = (List<T>) cachedImplementations.get(cls);
            return impls;
        }

        final ServiceLoader<T> serviceLoader = createOrGetServiceLoader(cls);
        return populateImplementationsCache(serviceLoader, cls);
    }



    private <T> List<T> populateImplementationsCache(final ServiceLoader<T> serviceLoader, final Class<T> cls) {
        synchronized(POPULATE_IMPL_LOCK) {
            if (cachedImplementations.containsKey(cls)) {
                @SuppressWarnings("unchecked")
                final List<T> impls = (List<T>) cachedImplementations.get(cls);
                return impls;
            }

            final List<T> impls = new ArrayList<>();
            for (T impl : serviceLoader) {
                impls.add(impl);
            }
            cachedImplementations.put(cls, impls);
            return impls;
        }
    }


    private <T> ServiceLoader<T> createOrGetServiceLoader(final Class<T> cls) {
        synchronized (SERVICE_LOADER_LOCK) {
            if (serviceLoaders.containsKey(cls)) {
                @SuppressWarnings("unchecked")
                final ServiceLoader<T> serviceLoader = (ServiceLoader<T>) serviceLoaders.get(cls);
                return serviceLoader;
            }

            final ServiceLoader<T> serviceLoader = ServiceLoader.load(cls);
            serviceLoaders.put(cls, serviceLoader);
            return serviceLoader;
        }
    }

    private static class Singleton {
        private static final ServiceProvider INSTANCE = new ServiceProvider();
    }
}
