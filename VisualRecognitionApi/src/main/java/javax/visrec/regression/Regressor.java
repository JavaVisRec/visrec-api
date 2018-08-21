package javax.visrec.regression;

public interface Regressor<I, R> {
    public R fit(I inputs); // 
}
