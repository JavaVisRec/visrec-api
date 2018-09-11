package javax.visrec.ml.data;

import javax.visrec.ml.data.norm.Normalizer;

/**
 *
 * @author zoran
 */
public class DataSetUtil {
    
    private DataSetUtil() { } 
    
    // scale values 
    // maybe just provide DataSet.normalize(new MaxNormalizer) , and dataSet injects itself into normalizer
    // or even better norm= new MaxNormalizer(dataSet); norm.normalize(); also separate construction from analysis
    public static <E> DataSet normalize(DataSet<E> dataSet, Normalizer norm) {
        norm.normalize(dataSet);
    }
    
    // normalizeMinMax
    // normalizeRange
    // how to specify which columns to normalize?
    
        // how will this method know about how to normalize specific type of elemeents?
        
    
    // retrun data set whith ddesired statistical properties
    // zero mean, one std
    public static <E> DataSet standardize(DataSet<E> dataSet) { // apply to all numer columns
        // how will this method know about how to normalize specific type of elemeents?
    }
    
    // this shoul ddefinitely be utility method
    public static DataSet removeDuplicates() {
        
    }

	transform()	- maybe can transorm into datas set whsle elements ar eof another type
	
//	statisticsSummary ()	-	mean std freq  by cols, maybe better go put it in dat aset class?
        // max, min, mean, std
    // returns true if data set is balanced (only needed for classification problems, not to go in Dataset interface)     
    public static boolean isBalanced(DataSet<?> dataSet) {  //use generic method to infer type of data set elements
        
    }	
    
    // summary - return basic statistics for each column in dat aset min, max, mean , mode, std, 1q, 3q
    
    /**
	addNoise with some noice generator
	balance(BalanceStrtegy)
	
	dimensionalityreuction    
    * /
    
    
}
