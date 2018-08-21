package javax.visrec.ml.data;

/**
 *
 * @author zoran
 */
public class DataSetUtil {
    
    private DataSetUtil() { } 
    
    // scale values 
    public <T>static DataSet normalize(Normalizer DataSet dataSet) {
        // how will this method know about how to normalize specific type of elemeents?
    }
    
    // retrun data set whith ddesired statistical properties
    // zero mean, one std
    public <T>static DataSet standardize(DataSet dataSet) {
        // how will this method know about how to normalize specific type of elemeents?
    }
    
    // this shoul ddefinitely be utility method
    public static DataSet removeDuplicates() {
        
    }

	transform()	- maybe can transorm into datas set whsle elements ar eof another type
	
	statisticsSummary ()	-	mean std freq  by cols, maybe better go put it in dat aset class?
    // returns true if data set is balanced (only needed for classification problems, not to go in Dataset interface)     
    public static boolean isBalanced(DataSet<?> dataSet) {  //use generic method to infer type of data set elements
        
    }	
    
    /**
	addNoise with some noice generator
	balance(BalanceStrtegy)
	
	dimensionalityreuction    
    * /
    
    
}
