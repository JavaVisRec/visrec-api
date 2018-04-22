package javax.visrec.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Utility class to optimize the use of the {@link ServiceLoader} API to find
 * implementations of the {@link ImageFactory} interface on the classpath.
 *
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public class ImageFactoryServiceLoader {

    /**
     * Singleton instance
     */
    private static ImageFactoryServiceLoader instance;

    /**
     * Contains all the {@link ImageFactory} implementations. The {@code IMAGE_CLASS} generic type is
     * the key of this map and the value is the implementation of the {@link ImageFactory}. All the implementations
     * within the cache are safe casted.
     */
    private final Map<Class<?>, ImageFactory<?>> imageFactoryImpls = new HashMap<>();

    /**
     * The "active" {@link ServiceLoader} used to find the implementations on the classpath.
     */
    private ServiceLoader<ImageFactory> activeServiceLoader = null;

    /**
     * Protected constructor to prevent instantiation of this class.
     */
    private ImageFactoryServiceLoader() {
        // Empty.
    }

    /**
     * Get the singleton instance of the class
     * @return An instance of {@link ImageFactoryServiceLoader}
     */
    public static ImageFactoryServiceLoader getInstance() {
        if (instance == null) {
            instance = new ImageFactoryServiceLoader();
        }
        return instance;
    }

    /**
     * Find the {@link ImageFactory} implementation which is associated with the given
     * {@code cls} parameter in the cached map of implementations and if the implementation can't be found in the map,
     * it will search for implementations on the classpath.
     *
     * The method will also try to cast the found {@link ImageFactory} to support the generic type used in the implementation.
     *
     * @param cls {@code Class} object which is associated to an implementation of {@link ImageFactory}.
     *                           {@code cls} may not be null.
     * @return {@code Optional<ImageFactory>} which may or may not contain the implementation depending on if it can
     * be found on the classpath or not.
     * @throws InvalidImageClassException If the {@link ImageFactory} can not be casted correctly.
     */
    public <T> Optional<ImageFactory<T>> findImageFactory(Class<T> cls) {
        Objects.requireNonNull(cls);
        ImageFactory<T> imageFactory = findCachedImageFactory(cls);
        if (imageFactory == null) {
            imageFactory = findImageFactoryFromServiceLoader(cls);
        }
        return Optional.ofNullable(imageFactory);
    }

    /**
     * Look through the cached implementations of {@link ImageFactory} to get the cached implementation
     * @param cls {@code Class} object which matches the generic type of the implementation.
     * @param <T> Generic Type of the implementation
     * @return {@link ImageFactory} instance if the implementation is cached or null.
     */
    @SuppressWarnings("unchecked")
    private <T> ImageFactory<T> findCachedImageFactory(final Class<T> cls) {
        final ImageFactory<?> imageFactory = imageFactoryImpls.get(cls);
        return imageFactory != null ? (ImageFactory<T>) imageFactory : null;
    }

    /**
     * Find the right {@link ImageFactory} implementation from the {@code ServiceLoader} API. It will only return the
     * implementation if the generic type of the implementation matches the {@code Class} used as value in the {@link Image}
     * annotation. The {@link Image} is mandatory to be present on the implementations.
     * @param cls {@code Class} to find the right implementation by.
     * @param <T> Generic Type on the implementation and {@code Class} used as value in the {@link Image} annotation.
     * @return A {@link ImageFactory} implementation or null.
     * @throws InvalidImageClassException If the {@link ImageFactory} could not be safely casted.
     */
    private <T> ImageFactory<T> findImageFactoryFromServiceLoader(final Class<T> cls) {
        final ServiceLoader<ImageFactory> serviceLoader = createOrGetServiceLoader();
        ImageFactory<?> foundImageFactory = null;
        for (ImageFactory<?> imageFactoryImpl : serviceLoader) {
            final Image imageAnnotation = imageFactoryImpl.getClass().getAnnotation(Image.class);
            if (Objects.nonNull(imageAnnotation) && cls.equals(imageAnnotation.value())) {
                imageFactoryImpls.put(cls, imageFactoryImpl);
                foundImageFactory = imageFactoryImpl;
                break;
            }
        }
        return foundImageFactory != null ? safeCastImageFactory(cls, foundImageFactory) : null;
    }

    /**
     * Safe cast the {@link ImageFactory} with a specific generic type. It will look for the generic type on the implementation
     * and checks if the given {@code cls} is equal to the found generic type.
     *
     * @param cls {@code Class} object which should match the generic type on the implementation
     * @param imageFactory {@link ImageFactory} implementation
     * @param <T> Generic Type used on the implementation.
     * @return A safe casted {@link ImageFactory}
     * @throws InvalidImageClassException If the generic type could not found or if {@code cls} does not
     * match the generic type.
     */
    @SuppressWarnings("unchecked")
    private <T> ImageFactory<T> safeCastImageFactory(Class<T> cls, ImageFactory<?> imageFactory) {
        final Class<?> foundCls = findGenericTypeOnImageFactory(imageFactory);
        if (Objects.isNull(foundCls)) {
            final String detailedMessage = String.format("Could not find generic type on '%s'",
                    imageFactory.getClass().getName());
            throw new InvalidImageClassException(detailedMessage);
        } else if (!cls.equals(foundCls)) {
            final String detailedMessage = String.format("Class from annotation '%s' does not match the generic type of '%s' on ' %s'",
                    cls.getName(), foundCls.getName(), imageFactory.getClass().getName());
            throw new InvalidImageClassException(detailedMessage);
        }
        return (ImageFactory<T>) imageFactory;
    }

    /**
     * Find the generic type on the implementation of {@link ImageFactory}. It only searches for the generic type
     * on the implemented interfaces without additional depths.
     * @param imageFactory The implementation
     * @return {@code Class} object if the generic type can be found otherwise null.
     */
    private Class<?> findGenericTypeOnImageFactory(final ImageFactory<?> imageFactory) {
        final Class<? extends ImageFactory> cls = imageFactory.getClass();
        Class<?> foundType = null;
        if (Objects.isNull(cls.getGenericInterfaces())) {
            return null;
        }

        final Type[] genericInterfaces = cls.getGenericInterfaces();
        if (genericInterfaces.length > 0) {
            final ParameterizedType parameterizedType = (ParameterizedType) genericInterfaces[0];
            if (parameterizedType.getActualTypeArguments().length > 0) {
                foundType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
            }
        }
        return foundType;
    }

    /**
     * Get or create a {@link ServiceLoader} of {@link ImageFactory}
     * @return A {@link ServiceLoader} instance with loaded classes of {@link ImageFactory}
     */
    private ServiceLoader<ImageFactory> createOrGetServiceLoader() {
        if (activeServiceLoader == null) {
            activeServiceLoader = ServiceLoader.load(ImageFactory.class);
        }
        return activeServiceLoader;
    }
}