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
