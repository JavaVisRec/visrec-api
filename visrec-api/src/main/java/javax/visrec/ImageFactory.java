package javax.visrec;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * This interface provides a standard way to get/read different(specified) kinds of image from file, url or input stream.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <T> the type of object to be returned after getting image from {@link File}, {@link URL} or {@link InputStream}
 * @since 1.0
 */
public interface ImageFactory<T> {

    /**
     * Retrieve the source through a {@link File} object and transform it into T.
     * @param file The source file.
     * @return T object.
     * @throws IOException If the file I/O went wrong or couldn't transform
     * the source into a T object.
     */
    T getImage(File file) throws IOException;

    /**
     * Retrieve the source through a {@link URL} object and transform it into T.
     * @param file The source.
     * @return T object.
     * @throws IOException If the I/O went wrong or couldn't transform
     * the source into a T object.
     */
    T getImage(URL file) throws IOException;

    /**
     * Retrieve the source through an {@link InputStream} object and transform it into T.
     * @param file The source.
     * @return T object.
     * @throws IOException If the I/O went wrong or couldn't transform
     * the source into a T object.
     */
    T getImage(InputStream file) throws IOException;
        
}
