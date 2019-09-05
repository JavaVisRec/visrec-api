package javax.visrec.ml.classification;

import java.util.Map;

public interface MultiClassClassifier<T, R> extends Classifier<T, Map<R, Float>>  {
    
}
