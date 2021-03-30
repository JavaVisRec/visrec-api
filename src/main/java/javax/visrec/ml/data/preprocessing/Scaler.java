package javax.visrec.ml.data.preprocessing;

import javax.visrec.ml.data.DataSet;

/**
 * Interface to perform scaling of a data set.
 * Scaling generally means to change the range of the values, while the shape of the distribution doesn’t change. 
 * Scaling data set features to range [0, 1] or similar is a common practice
 * in order to prepare data for machine learning training and reduce influence of different value ranges among data.
 * 
 * @param <T> Data set class (that implements DataSet interface)
 */
public interface Scaler<T extends DataSet<?>> {
    public void apply(T dataSet);    
}

