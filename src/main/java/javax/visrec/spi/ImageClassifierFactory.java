package javax.visrec.spi;

import javax.visrec.ml.model.ModelCreationException;
import javax.visrec.ml.classification.ImageClassifier;
import javax.visrec.ml.classification.NeuralNetImageClassifier;

public interface ImageClassifierFactory<T> {

    Class<T> getImageClass();

    ImageClassifier<T> create(NeuralNetImageClassifier.BuildingBlock<T> block) throws ModelCreationException;

}
