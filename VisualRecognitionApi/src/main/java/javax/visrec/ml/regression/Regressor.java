package javax.visrec.ml.regression;

/**
 * Predicts output based on specified input.
 * 
 * @author Zoran
 * @param <I> input type
 * @param <O> output type
 */
@FunctionalInterface
public interface Regressor <I, O> {
            public O predict(I input);
}
