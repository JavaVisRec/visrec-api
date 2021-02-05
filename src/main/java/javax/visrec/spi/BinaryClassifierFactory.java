package javax.visrec.spi;

import javax.visrec.ml.model.ModelCreationException;
import javax.visrec.ml.classification.BinaryClassifier;
import javax.visrec.ml.classification.NeuralNetBinaryClassifier;

public interface BinaryClassifierFactory<T> {

    Class<T> getTargetClass();

    BinaryClassifier<T> create(NeuralNetBinaryClassifier.BuildingBlock<T> block) throws ModelCreationException;

}
