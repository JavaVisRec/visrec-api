package javax.visrec.ml.classification;

import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.spi.ServiceProvider;
import java.io.File;
import java.util.Map;

/**
 * Binary classifier classifies object into one of two categories (for example: true/false, yes/no, red/blue, spam/not-spam, fraud/not-fraud).
 * Returns a probability that input object belongs to one of two classes.
 *
 * @author Zoran Sevarac
 */
public interface BinaryClassifier<T> extends Classifier<T, Map<String, Float>> {

    static <T> BinaryClassifier.Builder<T> builderOf(Class<T> targetCls) {
        return new BinaryClassifier.Builder<>(targetCls);
    }

    class BuildingBlock<T> {

        private Class<T> targetCls;
        private int inputsNum;
        private int[] hiddenLayers;
        private float maxError;
        private int maxEpochs;
        private float learningRate;
        private File trainingFile;

        private BuildingBlock() {
        }

        public Class<T> getTargetClass() {
            return targetCls;
        }

        public int getInputsNum() {
            return inputsNum;
        }

        public int[] getHiddenLayers() {
            return hiddenLayers;
        }

        public float getMaxError() {
            return maxError;
        }

        public int getMaxEpochs() {
            return maxEpochs;
        }

        public float getLearningRate() {
            return learningRate;
        }

        public File getTrainingFile() {
            return trainingFile;
        }
    }

    class Builder<T> {

        private BinaryClassifier.BuildingBlock<T> block;

        private Builder(Class<T> targetCls) {
            block = new BinaryClassifier.BuildingBlock<>();
            block.targetCls = targetCls;
        }

        public Builder<T> inputsNum(int inputsNum) {
            block.inputsNum = inputsNum;
            return this;
        }

        public Builder<T> hiddenLayers(int... hiddenLayers) {
            block.hiddenLayers = hiddenLayers;
            return this;
        }

        public Builder<T> maxError(float maxError) {
            block.maxError = maxError;
            return this;
        }

        public Builder<T> maxEpochs(int maxEpochs) {
            block.maxEpochs = maxEpochs;
            return this;
        }

        public Builder<T> learningRate(float learningRate) {
            block.learningRate = learningRate;
            return this;
        }

        public Builder<T> trainingFile(File trainingFile) {
            block.trainingFile = trainingFile;
            return this;
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
