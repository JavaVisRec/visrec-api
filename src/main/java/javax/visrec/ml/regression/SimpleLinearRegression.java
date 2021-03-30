package javax.visrec.ml.regression;

import java.util.Objects;
import javax.visrec.ml.model.ModelProvider;

/**
 * Simple linear regression finds the best possible straight line that tries to roughly describe given training set.
 * Mathematical formula for linear regression is: <b>prediction = slope * x + intercept</b>
 * which is a formula for linear function (straight line) and that's where the names comes from.
 * 
 * @param <MODEL_CLASS> Implementation class of the background model
 */
public abstract class SimpleLinearRegression<MODEL_CLASS> implements Regressor<Float, Float>, ModelProvider<MODEL_CLASS> {

    private MODEL_CLASS model;
    
    @Override
    public MODEL_CLASS getModel() {
        return model;
    }

    protected final void setModel(MODEL_CLASS model) {
        this.model = Objects.requireNonNull(model, "Model cannot bu null!");
    }
    
    @Override
    public abstract Float predict(Float input);    
    
    /**
     * Slope parameter of the model tells how much on average output change when input changes by one. 
     * 
     * If it is zero there is no linear dependency between input and output, and data is probably scattered.
     * If it is less then one output grows slower then input.
     * If it is greater than one, then output is growing faster than input.
     * If its negative, then output is lowering as input grows.
     * 
     * @return slope of the line produced by linear regression.
     */
    public abstract float getSlope();

    /**
     * Intercept tells us the value of prediction when input is zero.
     * 
     * @return 
     */
    public abstract float getIntercept();
    
}
