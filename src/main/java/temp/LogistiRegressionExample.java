package temp;

import java.util.Map;
import javax.visrec.ml.data.DataSet;

/**
 *
 * @author zoran
 */
public class LogistiRegressionExample {
    
    // set
    // with
    // use
    //nothing
    
    public static void main(String[] args) {
        // get data set
        DataSet<double[]> dataSet= null;// get data set from somewhere  -  usually csv file  BasicDataSet.fromCSVFile()
        DataSet<double[]> trainAndTestSet[] = dataSet.split(0.6);
        
        // build logistic regression classifier
        LogisticRegression logReg  = LogisticRegression.builder()
                                            .trainingSet(trainAndTestSet[0])    // naming: fromTrainingSet,withTrainingSet, forTrainingSet, with, use? 
                                            .build();
        
        // use to classify
        double[] someInput = new double[]{0.1, 0.2, 0.3};        
        Map<Boolean, Float> result = logReg.classify(someInput);
        
        // performa classifier performance evaluation
        // evaluate performance using test set
        // logReg.evaluate(trainAndTestSet[1]); // create internal evaluator, performa evaluation and return metrics
        
    }
    
}
