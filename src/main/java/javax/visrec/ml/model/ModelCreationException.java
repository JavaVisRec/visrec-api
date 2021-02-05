package javax.visrec.ml.model;

/**
 * Exception thrown if anything fails in the creation of a classifier.
 *
 * @author Kevin Berendsen
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
