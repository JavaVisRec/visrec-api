package javax.visrec.util;

import javax.visrec.ml.data.DataSet;

/**
 * Every model should provide methods for training and testing.
 * Not used anywhere at the moment.
 * @author zoran
 */
public interface Model {    
    void train(DataSet<?> trainingSet);
    void test(DataSet<?> testSet);    //   evaluate    
}
