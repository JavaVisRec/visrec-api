package javax.visrec.internal;

import javax.visrec.NoSuchGenericInterfaceException;
import javax.visrec.NoSuchGenericTypeArgumentException;
import javax.visrec.util.Image;
import javax.visrec.util.ImageFactory;
import java.util.*;

/**
 * Utility class to optimize the use of the {@link ServiceLoader} API to find
 * implementations of the {@link ImageFactory} interface on the classpath.
 *
 * The class is not thread safe but that doesn't matter because the implementations of the specification interface
 * are loaded in a thread safe manner in {@link ServiceProvider}.
 *
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public final class ImageFactoryProvider {

    /**
     * Contains all the {@link ImageFactory} implementations. The {@code IMAGE_CLASS} generic type is
     * the key of this map and the value is the implementation of the {@link ImageFactory}. All the implementations
     * within the cache are safe casted.
     */
    private final Map<Class<?>, ImageFactory<?>> imageFactoryImpls = new HashMap<>();


    /**
     * Protected constructor to prevent instantiation of this class.
     */
    private ImageFactoryProvider() {
        // Empty.
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
     * @throws NoSuchGenericInterfaceException If the {@link ImageFactory} can not be casted correctly.
     * @throws NoSuchGenericTypeArgumentException If the {@link ImageFactory} can not be casted correctly.
     */
    public <T> Optional<ImageFactory<T>> findImageFactory(Class<T> cls) {
        Objects.requireNonNull(cls);
        ImageFactory<T> imageFactory = findCachedImageFactory(cls);
        if (imageFactory == null) {
            imageFactory = findImageFactoryFromServiceProvider(cls);
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
     * @throws NoSuchGenericInterfaceException If the {@link ImageFactory} can not be casted correctly.
     * @throws NoSuchGenericTypeArgumentException If the {@link ImageFactory} can not be casted correctly.
     */
    private <T> ImageFactory<T> findImageFactoryFromServiceProvider(final Class<T> cls) {
        final List<ImageFactory> foundImpls = ServiceProvider.getInstance().findImplementations(ImageFactory.class);
        ImageFactory<?> foundImageFactory = null;
        for (ImageFactory<?> imageFactoryImpl : foundImpls) {
            final Image imageAnnotation = imageFactoryImpl.getClass().getAnnotation(Image.class);
            if (Objects.nonNull(imageAnnotation) && cls.equals(imageAnnotation.value())) {
                foundImageFactory = imageFactoryImpl;
                break;
            }
        }

        if (Objects.nonNull(foundImageFactory)) {
            SafeCastUtil.canSafelyCast(foundImageFactory, ImageFactory.class, cls);
            @SuppressWarnings("unchecked")
            final ImageFactory<T> castedImageFactory = (ImageFactory<T>) foundImageFactory;
            /*
            * If the happens two threads whom wishes to put the implementation
            * into the cached map, it will only add one because they'd have to wait for each turn
            * and the first will put the implementation into the cached map and the other one will ignore it
            * because it will only put the value into the map if the key is not present.
            */
            synchronized(this) {
                imageFactoryImpls.putIfAbsent(cls, castedImageFactory);
            }
            return castedImageFactory;
        }
        return null;
    }


    /**
     * Get the instance from the {@link ImageFactoryProvider}
     * @return {@link ImageFactoryProvider}
     */
    public static ImageFactoryProvider getInstance() {
        return Singleton.INSTANCE;
    }

    private static class Singleton {
        private static final ImageFactoryProvider INSTANCE = new ImageFactoryProvider();
    }
}