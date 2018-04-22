package javax.visrec.util;

/**
 * Exception thrown if the image class does not match
 * the generic type of {@link ImageFactory}
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public class InvalidImageClassException extends RuntimeException {

    /**
     * Constructs {@code InvalidImageClassException} with detail message and root cause.
     * @param message Detailed message in {@code String}
     * @param throwable Root cause of the exception to be thrown
     */
    public InvalidImageClassException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Constructs {@code InvalidImageClassException} with detail message.
     * @param message Detailed message in {@code String}
     */
    public InvalidImageClassException(final String message) {
        this(message, null);
    }
}
