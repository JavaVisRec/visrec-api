package javax.visrec.ml.classification;

import javax.visrec.util.ModelProvider;

/**
 * This class performs basic binary classification - mapping of specified input to true/false with probability 
 * using logistic regression algorithm. 
 * Subclasses should use specific logistic regression implementation to provide that functionality.
 *
 * @author Zoran Sevarac
 * @param <MODEL_CLASS> Implementation class of underlying machine learning model
 */
public abstract class LogisticRegression<MODEL_CLASS> implements BinaryClassifier<float[]>, ModelProvider<MODEL_CLASS> {

    private MODEL_CLASS model;

    @Override
    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }

}
