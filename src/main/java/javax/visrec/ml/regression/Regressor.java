package javax.visrec.ml.regression;

/**
 * Base interface for all regressors.
 * Regressors try to predict a continual value(s) (a decimal number) based on a set of inputs.
 *
 * @author Zoran Sevarac
 * @param <I> type of inputs / features
 * @param <R> return/result type
 */
public interface Regressor<I, R> {

    // rename to estimate?
    R predict(I inputs);

}
