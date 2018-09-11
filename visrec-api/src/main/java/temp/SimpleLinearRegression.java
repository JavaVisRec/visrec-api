package temp;

import javax.visrec.regression.Regressor;

/**
 *
 * @author zoran
 */
public class SimpleLinearRegression implements Regressor<double[], Double> {

    @Override
    public Double predict(double[] inputs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static SimpleLinearRegressionBuilder builder() {
        return new SimpleLinearRegressionBuilder();
    }
    
    public static class SimpleLinearRegressionBuilder {
        public SimpleLinearRegressionBuilder withInputs(int inputsCount) {
            
        }
    }
    
}
