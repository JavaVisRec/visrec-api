package javax.visrec.spi;

import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.ml.classification.ImageClassifier;

public interface ImageClassifierCreator<T> {

    Class<T> getImageClass();

    ImageClassifier<T> create(ImageClassifier.BuildingBlock<T> block) throws ClassifierCreationException;

}
