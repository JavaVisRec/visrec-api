package javax.visrec.ml.data.norm;

import javax.visrec.ml.data.DataSet;

/**
 * Interface to perform normalization/scaling of columns in data set
 * 
 * @author zoran
 */
public interface Normalizer {
    // specify param to determine inplace or copy normalization?
    public DataSet normalize(DataSet dataSet, boolean inplace);
    
    default DataSet normalize(DataSet dataSet) {
        return normalize(dataSet, true);
    }
}
