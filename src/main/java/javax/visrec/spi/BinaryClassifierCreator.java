package javax.visrec.spi;

import javax.visrec.ml.classification.BinaryClassifier;

public interface BinaryClassifierCreator<T> {

    Class<T> getTargetClass();

    BinaryClassifier<T> create(BinaryClassifier.BuildingBlock<T> block);

}
