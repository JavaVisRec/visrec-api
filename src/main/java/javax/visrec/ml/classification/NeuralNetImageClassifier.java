package javax.visrec.ml.classification;

import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.spi.ServiceProvider;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Map;

public interface NeuralNetImageClassifier<T> extends ImageClassifier<T> {

    static <IMAGE_CLASS> NeuralNetImageClassifier.Builder<IMAGE_CLASS> builder() {
        return new Builder<>();
    }

    class BuildingBlock<T> {

        private int imageWidth;
        private int imageHeight;
        private File networkArchitecture;
        private File trainingFile;
        private File labelsFile;
        private float maxError;
        private float learningRate;
        private Path exportPath;
        private Path importPath;
        private int maxEpochs;
        private Class<T> inputCls;

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

        public File getTrainingFile() {
            return trainingFile;
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

        public Path getExportPath() {
            return exportPath;
        }

        public Path getImportPath() {
            return importPath;
        }
        
        

        public int getMaxEpochs() {
            return maxEpochs;
        }

        public Class<T> getInputClass() { return inputCls; }

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

    class Builder<T> {

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

        public Builder<T> trainingFile(File trainingFile) {
            block.trainingFile = trainingFile;
            return this;
        }

        public Builder<T> labelsFile(File labelsFile) {
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
        
        public Builder<T> networkArchitecture(File architecture) {
            block.networkArchitecture = architecture;
            return this;
        }

        public BuildingBlock getBuildingBlock() {
            return block;
        }

        public ImageClassifier<T> build() throws ClassifierCreationException {
            return ServiceProvider.current().getClassifierFactoryService().createNeuralNetImageClassifier(block);
        }

        public ImageClassifier<T> build(Map<String, Object> configuration) throws ClassifierCreationException {
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
