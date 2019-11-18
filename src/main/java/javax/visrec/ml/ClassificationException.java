package javax.visrec.ml;

/**
 * Exception thrown if anything fails in classifying input using a classifier.
 *
 * @author Kevin Berendsen
 * @since 1.0
 */
public class ClassificationException extends Exception {

    /**
     * Creates a new instance of the exception
     * @param message additional message of the cause.
     * @param throwable caused by throwable.
     */
    public ClassificationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
