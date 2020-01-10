package javax.visrec.ml;

/**
 * Exception thrown if anything fails in the creation of a classifier.
 *
 * @author Kevin Berendsen
 * @since 1.0
 */
public class ClassifierCreationException extends Exception {

    /**
     * Creates a new instance of the exception
     * @param message additional message of the cause.
     */
    public ClassifierCreationException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of the exception
     * @param message additional message of the cause.
     * @param throwable caused by throwable.
     */
    public ClassifierCreationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
