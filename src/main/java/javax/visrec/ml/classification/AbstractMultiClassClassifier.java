package javax.visrec.ml.classification;

import javax.visrec.util.ModelProvider;

/**
 * 
 * @author Zoran
 * @param <MODEL_CLASS> class of machine learning model backend
 * @param <T>
 * @param <R> 
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
