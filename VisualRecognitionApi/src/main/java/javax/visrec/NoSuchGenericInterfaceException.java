package javax.visrec;

/**
 * Exception thrown if a generic interface can not be found on the object's class.
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public class NoSuchGenericInterfaceException extends RuntimeException {

    /**
     * Constructs {@code NoSuchGenericInterfaceException} with detail message and root cause.
     * @param message Detailed message in {@code String}
     * @param throwable Root cause of the exception to be thrown
     */
    public NoSuchGenericInterfaceException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Constructs {@code NoSuchGenericInterfaceException} with detail message.
     * @param message Detailed message in {@code String}
     */
    public NoSuchGenericInterfaceException(final String message) {
        this(message, null);
    }
}
