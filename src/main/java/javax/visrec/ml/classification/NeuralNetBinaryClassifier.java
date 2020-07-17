package javax.visrec.ml.classification;

import javax.visrec.spi.ServiceProvider;
import java.io.File;

public interface NeuralNetBinaryClassifier<T> extends BinaryClassifier<T> {

    static NeuralNetBinaryClassifier.Builder<?> builder() {
        return new NeuralNetBinaryClassifier.Builder<>();
    }

    class BuildingBlock<T> {
        private Class<T> inputCls;
        private int inputsNum;
        private int[] hiddenLayers;
        private float maxError;
        private int maxEpochs;
        private float learningRate;
        private File trainingFile;

        private BuildingBlock() {
        }

        public Class<T> getInputClass() {
            return inputCls;
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

        private static <R> BuildingBlock<R> copyWithNewTargetClass(BuildingBlock<?> block, Class<R> cls) {
            BuildingBlock<R> newBlock = new BuildingBlock<>();
            newBlock.inputCls = cls;
            newBlock.inputsNum = block.inputsNum;
            newBlock.hiddenLayers = block.hiddenLayers;
            newBlock.maxError = block.maxError;
            newBlock.maxEpochs = block.maxEpochs;
            newBlock.learningRate = block.learningRate;
            newBlock.trainingFile = block.trainingFile;
            return newBlock;
        }
    }

    class Builder<T> implements javax.visrec.util.Builder<BinaryClassifier<T>, ClassifierCreationException> {

        private NeuralNetBinaryClassifier.BuildingBlock<T> block;

        private Builder() {
            this(new NeuralNetBinaryClassifier.BuildingBlock<>());
        }

        private Builder(BuildingBlock<T> block) {
            this.block = block;
        }

        public <R> Builder<R> inputClass(Class<R> cls) {
            BuildingBlock<R> newBlock = BuildingBlock.copyWithNewTargetClass(block, cls);
            return new Builder<>(newBlock);
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

        public NeuralNetBinaryClassifier.BuildingBlock<T> getBuildingBlock() {
            return block;
        }

        public BinaryClassifier<T> build() throws ClassifierCreationException {
            return ServiceProvider.current().getClassifierFactoryService().createNeuralNetBinaryClassifier(block);
        }
    }
}
