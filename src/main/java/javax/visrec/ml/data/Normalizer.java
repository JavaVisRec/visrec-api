package javax.visrec.ml.data;

/**
 * Interface to perform normalization/scaling of data set
 * 
 * @author Zoran Sevarac
 * @param <T> Data set class (that implements DataSet interface)
 */
public interface Normalizer<T extends DataSet<?>> {
    public void normalize(T dataSet);    
}

