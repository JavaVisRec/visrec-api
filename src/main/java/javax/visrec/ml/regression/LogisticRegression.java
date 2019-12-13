package javax.visrec.ml.regression;

import javax.visrec.ml.classification.DeprecatedBinaryClassifier;

/**
 * This class performs basic binary classification - mapping of specified input to true/false with probability.
 *
 * @author Zoran Sevarac
 * @param <MODEL_CLASS> Implementation class of underlying machine learning model
 */
public abstract class LogisticRegression<MODEL_CLASS> implements DeprecatedBinaryClassifier<float[]> {

    private MODEL_CLASS model;

    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }

    // add train method here so we can dow model.train()
}
