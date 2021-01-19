package javax.visrec.ml.classification;

import javax.visrec.spi.ServiceProvider;
import java.io.File;
import java.nio.file.Path;

public interface NeuralNetImageClassifier<T> extends ImageClassifier<T> {

    static <IMAGE_CLASS> NeuralNetImageClassifier.Builder<IMAGE_CLASS> builder() {
        return new Builder<>();
    }

    class BuildingBlock<T> {

        private int imageWidth;
        private int imageHeight;
        private Path networkArchitecture;
        private Path trainingFile;
        private Path labelsFile;
        private float maxError;
        private float learningRate;
        private Path exportPath;
        private Path importPath;
        private int maxEpochs;
        private Class<T> inputCls;

        private BuildingBlock() {
        }

        public Path getNetworkArchitecture() {
            return networkArchitecture;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public Path getTrainingFile() {
            return trainingFile;
        }

        public Path getLabelsFile() {
            return labelsFile;
        }

        public float getMaxError() {
            return maxError;
        }

        public float getLearningRate() {
            return learningRate;
        }

        public Path getExportPath() {
            return exportPath;
        }

        public Path getImportPath() {
            return importPath;
        }


        public int getMaxEpochs() {
            return maxEpochs;
        }

        public Class<T> getInputClass() {
            return inputCls;
        }

        private static <R> BuildingBlock<R> copyWithNewInputClass(BuildingBlock<?> block, Class<R> cls) {
            BuildingBlock<R> newBlock = new BuildingBlock<>();
            newBlock.inputCls = cls;
            newBlock.imageHeight = block.imageHeight;
            newBlock.imageWidth = block.imageWidth;
            newBlock.labelsFile = block.labelsFile;
            newBlock.exportPath = block.exportPath;
            newBlock.importPath = block.importPath;
            newBlock.networkArchitecture = block.networkArchitecture;
            newBlock.maxError = block.maxError;
            newBlock.maxEpochs = block.maxEpochs;
            newBlock.learningRate = block.learningRate;
            newBlock.trainingFile = block.trainingFile;
            return newBlock;
        }
    }

    class Builder<T> implements javax.visrec.util.Builder<ImageClassifier<T>, ClassifierCreationException> {

        private BuildingBlock<T> block;

        private Builder() {
            block = new BuildingBlock<>();
        }

        private Builder(BuildingBlock<T> block) {
            this.block = block;
        }

        public <R> Builder<R> inputClass(Class<R> cls) {
            BuildingBlock<R> newBlock = BuildingBlock.copyWithNewInputClass(block, cls);
            return new Builder<>(newBlock);
        }

        public Builder<T> imageWidth(int imageWidth) {
            block.imageWidth = imageWidth;
            return this;
        }

        public Builder<T> imageHeight(int imageHeight) {
            block.imageHeight = imageHeight;
            return this;
        }

        public Builder<T> trainingFile(Path trainingFile) {
            block.trainingFile = trainingFile;
            return this;
        }

        public Builder<T> labelsFile(Path labelsFile) {
            block.labelsFile = labelsFile;
            return this;
        }

        public Builder<T> maxError(float maxError) {
            block.maxError = maxError;
            return this;
        }

        public Builder<T> maxEpochs(int epochs) {
            block.maxEpochs = epochs;
            return this;
        }

        public Builder<T> learningRate(float learningRate) {
            block.learningRate = learningRate;
            return this;
        }

        public Builder<T> exportModel(Path path) {
            block.exportPath = path;
            return this;
        }

        public Builder<T> importModel(Path path) {
            block.importPath = path;
            return this;
        }

        public Builder<T> networkArchitecture(Path architecture) {
            block.networkArchitecture = architecture;
            return this;
        }

        public BuildingBlock<T> getBuildingBlock() {
            return block;
        }

        public ImageClassifier<T> build() throws ClassifierCreationException {
            return ServiceProvider.current().getClassifierFactoryService().createNeuralNetImageClassifier(block);
        }
    }
}
