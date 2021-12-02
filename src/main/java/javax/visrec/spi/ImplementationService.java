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
 
package javax.visrec.spi;

/**
 * Returns information about the used implementation of visual recognition API
 *
 * @since 1.0
 */
public abstract class ImplementationService {

    /**
     * Get the name of the implementation
     * @return name as {@code String}
     */
    public abstract String getName();

    /**
     * Get the version of the implementation
     * @return version as {@code String}
     */
    public abstract String getVersion();

    /**
     * Returns the name, major and minor version of the implementation
     * @return combined information in a {@code String}
     */
    @Override
    public final String toString() {
        return getName() + " " + getVersion();
    }
}
