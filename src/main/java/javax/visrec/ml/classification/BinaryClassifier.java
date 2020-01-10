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



    static <T> BinaryClassifier.Builder<T> builderOf(Class<T> targetCls) {
        return new BinaryClassifier.Builder<>(targetCls);
    }

    class BuildingBlock<T> {

        private Class<T> targetCls;

        private BuildingBlock() {
        }

        public Class<T> getTargetClass() {
            return targetCls;
        }
    }

    class Builder<T> {

        private BinaryClassifier.BuildingBlock<T> block;

        private Builder(Class<T> targetCls) {
            block = new BinaryClassifier.BuildingBlock<>();
            block.targetCls = targetCls;
        }

        public BinaryClassifier.BuildingBlock<T> getBuildingBlock() {
            return block;
        }

        public BinaryClassifier<T> build() throws ClassifierCreationException {
            return ServiceProvider.current().getClassifierCreatorService().createBinaryClassifier(block);
        }

        public BinaryClassifier<T> build(Map<String, Object> configuration) throws ClassifierCreationException {
            throw new IllegalStateException("not implemented yet");
        }
    }

}
