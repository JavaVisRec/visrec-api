package javax.visrec.ml.classification;

import javax.visrec.ml.model.ModelProvider;

/**
 * Skeleton base class for implementations of multi class classifiers.
 * 
 * @param <MODEL_CLASS> class of machine learning model back-end
 * @param <T> type of classifier's input
 * @param <R> type of classifier's results/class
 */
public abstract class AbstractMultiClassClassifier<MODEL_CLASS, T, R> implements MultiClassClassifier<T, R>, ModelProvider<MODEL_CLASS> {

    private MODEL_CLASS model;

    @Override
    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }

}
