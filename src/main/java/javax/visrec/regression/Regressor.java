package javax.visrec.regression;

/**
 * Base interface for all regressors.
 * Regressors try to predict a continual value(s) (a decimal number) based on a set of inputs.
 * 
 * @author Zoran Sevarac
 * @param <I> input type
 * @param <R> return/result tupe
 */
public interface Regressor<I, R> {    
    
    public R predict(I inputs);

}
