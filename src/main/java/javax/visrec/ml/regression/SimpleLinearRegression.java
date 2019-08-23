package javax.visrec.ml.regression;

import javax.visrec.ml.regression.Regressor;

/**
 * Simple linear regression finds the best possible straight line that tries to explain given training set.
 * 
 * @author zoran
 */
public abstract class SimpleLinearRegression<MODEL_CLASS> implements Regressor<Float, Float> {

    private MODEL_CLASS model;
    
    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }
    
    @Override
    public abstract Float predict(Float inputs);    
    
    /**
     * How much on average output change when input changes by one. 
     * If it is zero there is no linear dependency between input and output, and data is probably scattered.
     * If it is less then one output grows slower then input.
     * If it is greater than one, then output is growing faster than input.
     * If its negative, then output is lowering as input grows.
     * 
     * @return
     */
    public abstract float getSlope();

    /**
     * The value of output when input is zero
     * @return 
     */
    public abstract float getIntercept();
    // ili da vracam parametre modela u mapi?
    // ili kao niz koeficijenata?

    // performance measures
    // RSE
    // R2    
    

//    Map.new().put()
    
}
