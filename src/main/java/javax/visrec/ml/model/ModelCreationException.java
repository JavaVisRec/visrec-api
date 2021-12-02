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

/**
 * Exception thrown if anything fails in the creation of a classifier.
 *
 * @since 1.0
 */
public class ModelCreationException extends Exception {

    /**
     * Creates a new instance of the exception
     * @param message additional message of the cause.
     */
    public ModelCreationException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of the exception
     * @param message additional message of the cause.
     * @param throwable caused by throwable.
     */
    public ModelCreationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
