package javax.visrec.ml.data;

/**
 * Interface to perform scaling of a data set.
 * Scaling data set features to range [0, 1] or similar is a common practice
 * in order to prepare data for machine learning training and reduce influence of different scale among data.
 * 
 * @author Zoran Sevarac
 * @param <T> Data set class (that implements DataSet interface)
 */
public interface Scaler<T extends DataSet<?>> {
    public void scale(T dataSet);    
}

