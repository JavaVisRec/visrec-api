package javax.visrec.spi;

/**
 * Returns information about the used implementation of visual recognition API
 * @author Kevin Berendsen
 * @since 1.0
 */
public abstract class ImplementationService {

    /**
     * Get the name of the implementation
     * @return name as {@code String}
     */
    public abstract String getName();

    /**
     * Get the major version of the implementation
     * @return major version as {@code int}
     */
    public abstract int getMajorVersion();

    /**
     * Get the minor version of the implementation
     * @return minor version as {@code int}
     */
    public abstract int getMinorVersion();

    /**
     * Returns the name, major and minor version of the implementation
     * @return combined information in a {@code String}
     */
    @Override
    public final String toString() {
        return getName() + " " + getMajorVersion() + "." + getMinorVersion();
    }
}
