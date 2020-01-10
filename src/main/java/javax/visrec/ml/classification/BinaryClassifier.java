package javax.visrec.ml.classification;

import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.spi.ServiceProvider;
import java.util.Map;

/**
 * Binary classifier classifies object into one of two categories (for example: true/false, yes/no, red/blue, spam/not-spam, fraud/not-fraud).
 * Returns a probability that input object belongs to one of two classes.
 *
 * @author Zoran Sevarac
 */
public interface BinaryClassifier<T> extends Classifier<T, Float> {



    static BinaryClassifier.Builder builder() {
        return new BinaryClassifier.Builder();
    }

    class BuildingBlock {

        private BuildingBlock() {
        }
    }

    class Builder {

        private BinaryClassifier.BuildingBlock block;

        private Builder() {
            block = new BinaryClassifier.BuildingBlock();
        }

        public BinaryClassifier.BuildingBlock getBuildingBlock() {
            return block;
        }

        public BinaryClassifier build() throws ClassifierCreationException {
            return ServiceProvider.current().getClassifierService().createBinaryClassifier(block);
        }

        public ImageClassifier build(Map<String, Object> configuration) throws ClassifierCreationException {
            throw new IllegalStateException("not implemented yet");
        }
    }

}
