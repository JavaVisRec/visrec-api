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

package javax.visrec;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

/**
 * This interface provides a standard way to get/read different(specified) kinds of image from file, url or input stream.
 *
 * @param <T> the type of object to be returned after getting image from {@link Path}, {@link URL} or {@link InputStream}
 * @since 1.0
 */
public interface ImageFactory<T> {

    /**
     * Retrieve the source through a {@link Path} object and transform it into T.
     *
     * @param path The source file.
     * @return T object.
     * @throws IOException If the file I/O went wrong or couldn't transform
     *                     the source into a T object.
     */
    T getImage(Path path) throws IOException;

    /**
     * Retrieve the source through a {@link URL} object and transform it into T.
     *
     * @param file The source.
     * @return T object.
     * @throws IOException If the I/O went wrong or couldn't transform
     *                     the source into a T object.
     */
    T getImage(URL file) throws IOException;

    /**
     * Retrieve the source through an {@link InputStream} object and transform it into T.
     *
     * @param file The source.
     * @return T object.
     * @throws IOException If the I/O went wrong or couldn't transform
     *                     the source into a T object.
     */
    T getImage(InputStream file) throws IOException;

}
