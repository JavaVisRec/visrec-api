package javax.visrec.ml.classification;

import javax.visrec.ml.model.ModelCreationException;
import javax.visrec.spi.ServiceProvider;
import java.nio.file.Path;

public interface NeuralNetImageClassifier<T> extends ImageClassifier<T> {

    static <IMAGE_CLASS> NeuralNetImageClassifier.Builder<IMAGE_CLASS> builder() {
        return new Builder<>();
    }

    class BuildingBlock<T> {

        private int imageWidth;
        private int imageHeight;
        private Path networkArchitecture;
        private Path trainingPath;
        private Path labelsFile;
        private float maxError;
        private float learningRate;
        private Path exportPath;
        private Path importPath;
        private int maxEpochs;
        private float threshold;        
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

        public Path getTrainingPath() {
            return trainingPath;
        }

        public Path getLabelsPath() {
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

        public float getThreshold() {
            return threshold;
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
            newBlock.trainingPath = block.trainingPath;
            newBlock.threshold = block.threshold;
            return newBlock;
        }
    }

    class Builder<T> implements javax.visrec.ml.model.ModelBuilder<ImageClassifier<T>> {

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
            block.trainingPath = trainingFile;
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
        
        public Builder<T> threshold(float threshold) {
            block.threshold = threshold;
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

        public ImageClassifier<T> build() throws ModelCreationException {
            return ServiceProvider.current().getClassifierFactoryService().createNeuralNetImageClassifier(block);
        }
    }
}
