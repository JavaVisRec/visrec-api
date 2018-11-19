package javax.visrec.regression;

public interface Regressor<I, R> {    
    public R predict(I inputs); // is this good method name?
}
