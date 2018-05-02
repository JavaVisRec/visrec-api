package javax.visrec;

/**
 * Exception thrown if a type can not be found as argument in the generic interface.
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public class NoSuchGenericTypeArgumentException extends RuntimeException {

    /**
     * Constructs {@code NoSuchGenericInterfaceException} with detail message and root cause.
     * @param message Detailed message in {@code String}
     * @param throwable Root cause of the exception to be thrown
     */
    public NoSuchGenericTypeArgumentException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Constructs {@code NoSuchGenericInterfaceException} with detail message.
     * @param message Detailed message in {@code String}
     */
    public NoSuchGenericTypeArgumentException(final String message) {
        this(message, null);
    }
}
