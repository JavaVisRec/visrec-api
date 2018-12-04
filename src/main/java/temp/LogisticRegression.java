package temp;

import java.util.Map;
import java.util.Properties;
import javax.visrec.ml.classification.Classifier;
import javax.visrec.ml.data.DataSet;
import javax.visrec.util.Builder;

/**
 * This class performs basic binary classification - mapping of specified input to true/false with probability.
 * 
 * @author Zoran Sevarac
 */
public class LogisticRegression implements Classifier<double[], Boolean>{

    @Override
    public Map<Boolean, Float> classify(double[] someInput) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static class LogisticRegressionBuilder implements Builder<LogisticRegression> {

        @Override
        public LogisticRegression build(Properties prop) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public LogisticRegression build() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public LogisticRegressionBuilder trainingSet(DataSet<double[]> traininGSet) {
            return this;
        }
        
    } 

    public static LogisticRegressionBuilder builder() {
            return new LogisticRegressionBuilder();
    }
    
}
