package javax.visrec.ml.classification;

public abstract class AbstractMultiClassClassifier<MODEL_CLASS, T, R> implements MultiClassClassifier<T, R> {

    private MODEL_CLASS model;

    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }

}
