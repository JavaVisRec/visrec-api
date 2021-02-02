package javax.visrec.ml.classification;

/**
 * Exception thrown if anything fails in the execution of a classifier.
 *
 * @author Kevin Berendsen
 * @since 1.0
 */
public class ClassificationException extends RuntimeException {

    /**
     * Creates a new instance of the exception
     *
     * @param message additional message of the cause.
     */
    public ClassificationException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of the exception
     *
     * @param message   additional message of the cause.
     * @param throwable caused by throwable.
     */
    public ClassificationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
