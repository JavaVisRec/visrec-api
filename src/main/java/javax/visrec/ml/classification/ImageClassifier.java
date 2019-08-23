package javax.visrec.ml.classification;

import javax.visrec.spi.ServiceProvider;
import java.awt.image.BufferedImage;
import java.io.File;

public interface ImageClassifier<IMAGE_CLASS> extends Classifier<IMAGE_CLASS, String> {

    static ImageClassifier.Builder newBuilder() {
        return new Builder();
    }

    class BuildingBlock {

        private int imageWidth;
        private int imageHeight;
        private File trainingsFile;
        private File labelsFile;
        private float maxError;
        private float learningRate;
        private File modelFile;

        private BuildingBlock() {
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public File getTrainingsFile() {
            return trainingsFile;
        }

        public File getLabelsFile() {
            return labelsFile;
        }

        public float getMaxError() {
            return maxError;
        }

        public float getLearningRate() {
            return learningRate;
        }

        public File getModelFile() {
            return modelFile;
        }
    }

    class Builder implements javax.visrec.util.Builder<ImageClassifier> {

        private BuildingBlock block;

        private Builder() {
            block = new BuildingBlock();
        }

        public Builder imageWidth(int imageWidth) {
            block.imageWidth = imageWidth;
            return this;
        }

        public Builder imageHeight(int imageHeight) {
            block.imageHeight = imageHeight;
            return this;
        }

        public Builder trainingsFile(File trainingsFile) {
            block.trainingsFile = trainingsFile;
            return this;
        }

        public Builder labelsFile(File labelsFile) {
            block.labelsFile = labelsFile;
            return this;
        }

        public Builder maxError(float maxError) {
            block.maxError = maxError;
            return this;
        }

        public Builder learningRate(float learningRate) {
            block.learningRate = learningRate;
            return this;
        }

        public Builder modelFile(File modelFile) {
            block.modelFile = modelFile;
            return this;
        }

        @Override
        public ImageClassifier build() {
            return ServiceProvider.current().getClassifierService().createImageClassifier(block);
        }
    }

}
