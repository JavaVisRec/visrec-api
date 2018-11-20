package javax.visrec.ml.data;

import javax.visrec.ml.data.norm.Normalizer;

/**
 * Utility class that provides commmon operations on data sets
 * @author zoran
 */
public class DataSets {
    
    private DataSets() { } 
    
    // scale values 
    // maybe just provide DataSet.normalize(new MaxNormalizer) , and dataSet injects itself into normalizer
    // or even better norm= new MaxNormalizer(dataSet); norm.normalize(); also separate construction from analysis
    public static <E> DataSet normalize(DataSet<E> dataSet, Normalizer norm) {
        return norm.normalize(dataSet, false);
    }

    // how about moving thes estatic methods to coresponding interface?
//    public static <E> DataSet normalizMax(DataSet<E> dataSet) {
//        Normalizer norm = new MaxNormalizer(dataSet); // perform analysys of data set (find max values)
//        return norm.normalize(dataSet, false); // perfrom normalization and return as new data set
//    }
    
    // normalizeMinMax
    // normalizeRange
    // how to specify which columns to normalize?
    
        // how will this method know about how to normalize specific type of elemeents?
        
    
    // retrun data set whith ddesired statistical properties
    // zero mean, one std
    public static <E> DataSet standardize(DataSet<E> dataSet) { // apply to all numer columns
        // how will this method know about how to normalize specific type of elemeents?
        throw new UnsupportedOperationException("not implemented");
    }
    
    // this shoul ddefinitely be utility method
    public static DataSet removeDuplicates() {
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
