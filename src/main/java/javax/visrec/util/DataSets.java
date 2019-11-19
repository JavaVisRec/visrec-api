package javax.visrec.util;

import javax.visrec.ml.data.Normalizer;
import javax.visrec.ml.data.DataSet;

/**
 * Utility methods that provides common operations on data sets.
 * Will probably be deprecated or figure out how to expose implementation specific operations
 * 
 * @author Zoran Sevarac
 */
@Deprecated
public class DataSets {
    
    private DataSets() { } 
    
    // method to get basic statistics - summary() mead median, mi , max std, qt
    
    // scale values 
    // maybe just provide DataSet.normalize(new MaxNormalizer) , and dataSet injects itself into normalizer
    // or even better norm= new MaxNormalizer(dataSet); norm.normalize(); also separate construction from analysis

    public static <T extends DataSet<?>> void normalize(T dataSet, Normalizer<T> norm) {
        norm.normalize(dataSet);
    }

    // how about moving thes estatic methods to coresponding interface?
//    public static <T extends DataSet<?>> void normalizeMax(DataSet<E> dataSet) {
//        Normalizer norm = new MaxNormalizer(dataSet); // perform analysys of data set (find max values)
//        return norm.normalize(dataSet, false); // perfrom normalization and return as new data set
//    }
    
//    public static <E> DataSet<E> normalizeMinMax(DataSet<E> dataSet) {
//        Normalizer norm = new MinMaxNormalizer(dataSet); // perform analysys of data set (find max values)
//        return norm.normalize(dataSet, false); // perfrom normalization and return as new data set
//    }    

//    public static <E> DataSet<E> normalizeRange(DataSet<E> dataSet, float low, float high) {
//        Normalizer norm = new MinMaxNormalizer(dataSet); // perform analysys of data set (find max values)
//        return norm.normalize(dataSet, false); // perfrom normalization and return as new data set
//    }    
    

    // how to specify which columns to normalize? do we need to? just normalize all
    // how will this method know about how to normalize specific type of elemeents? eg. User? or  this assumes only numeric values
        
    
    // retrun data set whith ddesired statistical properties
    // zero mean, one std
    public static <T extends DataSet<?>> void standardize(T dataSet) { // apply to all numer columns
        // how will this method know about how to normalize specific type of elemeents?
        throw new UnsupportedOperationException("not implemented");
    }
    
    // this shoul ddefinitely be utility method
    public static <E> DataSet<E> removeDuplicates() {
        throw new UnsupportedOperationException("not implemented");
    }

    //transform()	- maybe can transorm into datas set whsle elements ar eof another type
	
//	statisticsSummary ()	-	mean std freq  by cols, maybe better go put it in dat aset class?
        // max, min, mean, std
    // returns true if data set is balanced (only needed for classification problems, not to go in Dataset interface)     
    public static boolean isBalanced(DataSet<?> dataSet) {  //use generic method to infer type of data set elements
        throw new UnsupportedOperationException("not implemented");
    }

    // summary - return basic statistics for each column in dat aset min, max, mean , mode, std, 1q, 3q
    
    /**
	addNoise with some noice generator
	balance(BalanceStrtegy)
	
	dimensionalityreuction    
    */
    
}
