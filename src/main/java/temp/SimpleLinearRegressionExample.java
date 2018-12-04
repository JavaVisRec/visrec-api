package temp;

/**
 *
 * @author zoran
 */
public class SimpleLinearRegressionExample {
    
    public static void main(String[] args) {
        SimpleLinearRegression linReg = SimpleLinearRegression.builder()
                                                    .withTrainingSet(trainingSet)    
                                                    //.withInputColumn("budget")    maybe set these on dataSet
                                                    //.withTargetColumn("sales")                                                    
                                                    .build();    
        
        double[] someInput = new double[]{0.1, 0.2, 0.3};  
        double result = linReg.predict(someInput);
        

        double slope = linReg.getSlope();
        double intercept = linReg.getIntercept();      
        
        // TODO: plot data set and  line
        
    }
    
}
