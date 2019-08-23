package javax.visrec.ml.data;

import javax.visrec.ml.data.DataSet;

/**
 * Interface to perform normalization/scaling of data set
 * 
 * @author Zoran Sevarac
 * @param <T> Data set class (that implements DataSet interface)
 */
public interface Normalizer<T extends DataSet<?>> {
    public void normalize(T dataSet);    
    // public T normalize(T dataSet);  // should we return normalized data set or perfrom inplace normalization in this interface  
}

