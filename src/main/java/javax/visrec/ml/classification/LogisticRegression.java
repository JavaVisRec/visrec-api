package javax.visrec.ml.classification;

import java.util.Objects;
import javax.visrec.ml.model.ModelProvider;

/**
 * This class performs basic binary classification - mapping of specified input to true/false with probability 
 * using logistic regression algorithm. 
 * Subclasses should use specific logistic regression implementation to provide that functionality.
 *
 * @param <MODEL_CLASS> Implementation class of underlying machine learning model
 */
public abstract class LogisticRegression<MODEL_CLASS> implements BinaryClassifier<float[]>, ModelProvider<MODEL_CLASS> {

    private MODEL_CLASS model;

    @Override
    public MODEL_CLASS getModel() {
        return model;
    }
    
    protected final void setModel(MODEL_CLASS model) {
        this.model = Objects.requireNonNull(model, "Model cannot be null!");
    }    

}
