package javax.visrec.ml.regression;

/**
 * Predicts output based on specified input.
 * 
 * @author Zoran
 */
@FunctionalInterface
public interface Regression <I, O> {
            public O predict(I input);
}
