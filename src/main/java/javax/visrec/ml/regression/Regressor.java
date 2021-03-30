package javax.visrec.ml.regression;

/**
 * Regressor tries to predict a numeric value based on a given set of inputs.
 * This is a base interface for all regressors.
 * Implementations should specify specific type of inputs and outputs that
 * specific algorithm expects and returns.
 * 
 * @param <I> type of inputs / features
 * @param <R> return/result type
 */
public interface Regressor<I, R extends Number> {

    R predict(I inputs);

}
