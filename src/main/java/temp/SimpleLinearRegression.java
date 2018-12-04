package temp;

import javax.visrec.regression.Regressor;

/**
 *
 * @author zoran
 */
public class SimpleLinearRegression implements Regressor<double[], Double> {

    @Override
    public Double predict(double[] inputs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static SimpleLinearRegressionBuilder builder() {
        return new SimpleLinearRegressionBuilder();
    }
    
    public static class SimpleLinearRegressionBuilder {
        public SimpleLinearRegressionBuilder withInputs(int inputsCount) {
            throw new UnsupportedOperationException("not implemented");
        }
    }
    
}
