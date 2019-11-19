package javax.visrec.ml.classification;

import javax.visrec.ml.ClassificationException;
import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.spi.ServiceProvider;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public interface ImageClassifier {

    Map<String, Float> classify(BufferedImage input) throws ClassificationException;

    Map<String, Float> classify(File input) throws ClassificationException;

    Map<String, Float> classify(InputStream input) throws ClassificationException;

    static ImageClassifier.Builder builder() {
        return new Builder();
    }

    class BuildingBlock {

        private int imageWidth;
        private int imageHeight;
        private File networkArchitecture;
        private File trainingsFile;
        private File labelsFile;
        private float maxError;
        private float learningRate;
        private File modelFile;
        private int maxEpochs;

        private BuildingBlock() {
        }

        public File getNetworkArchitecture() {
            return networkArchitecture;
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

        public int getMaxEpochs() {
            return maxEpochs;
        }
    }

    class Builder {

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

        public Builder maxEpochs(int epochs) {
            block.maxEpochs = epochs;
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

        public Builder networkArchitecture(File architecture) {
            block.networkArchitecture = architecture;
            return this;
        }

        public ImageClassifier build() throws ClassifierCreationException {
            return ServiceProvider.current().getClassifierService().createImageClassifier(block);
        }

        public ImageClassifier build(Map<String, Object> configuration) throws ClassifierCreationException {
            Method[] methods = this.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (!method.getName().equals("build") && method.getParameterCount() == 1
                        && configuration.containsKey(method.getName())) {
                    try {
                        Object value = configuration.get(method.getName());
                        Class<?> expectedParameterType = method.getParameterTypes()[0];
                        // Integer casting
                        if (expectedParameterType.equals(int.class) || expectedParameterType.equals(Integer.class)) {
                            if (value instanceof String) {
                                method.invoke(this, Integer.parseInt((String) value));
                                continue;
                            }
                        }

                        // Float casting
                        if (expectedParameterType.equals(float.class) || expectedParameterType.equals(Float.class)) {
                            if (value instanceof String) {
                                method.invoke(this, Float.parseFloat((String) value));
                                continue;
                            }
                        }

                        // File casting
                        if (expectedParameterType.equals(File.class)) {
                            if (value instanceof String) {
                                method.invoke(this, new File((String) value));
                                continue;
                            }
                        }

                        // Others
                        method.invoke(this, value);
                    } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                        throw new ClassifierCreationException("Couldn't invoke '" + method.getName() + "'", e);
                    }
                }
            }
            return build();
        }
    }

}
