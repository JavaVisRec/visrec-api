package javax.visrec.spi;

import javax.visrec.ImageFactory;
import java.util.Optional;

/**
 * The service to locate and find implementations of the {@link ImageFactory} interface.
 *
 * @author Kevin Berendsen
 * @since 1.0
 */
public interface ImageFactoryService {

    /**
     * Get the {@link ImageFactory} implementation by its image type.
     * @param imageCls image type in {@link Class} object which is able to
     *                 be processed by the image factory implementation.
     * @param <T> image type
     * @return {@link Optional} with possible {@link ImageFactory} implementation
     * if found.
     */
    <T> Optional<ImageFactory<T>> getByImageType(final Class<T> imageCls);

}
