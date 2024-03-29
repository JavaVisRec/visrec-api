/**
 * Visual Recognition API for Java, JSR381
 * Copyright (C) 2020  Zoran Sevarac, Frank Greco
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
 
package javax.visrec.ml.classification;

import javax.visrec.ml.model.ModelCreationException;
import javax.visrec.spi.ServiceProvider;
import java.nio.file.Path;

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
        private Path trainingPath;
        private float threshold;

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

        public Path getTrainingPath() {
            return trainingPath;
        }

        public float getThreshold() {
            return threshold;
        }
        
        

        private static <R> BuildingBlock<R> copyWithNewTargetClass(BuildingBlock<?> block, Class<R> cls) {
            BuildingBlock<R> newBlock = new BuildingBlock<>();
            newBlock.inputCls = cls;
            newBlock.inputsNum = block.inputsNum;
            newBlock.hiddenLayers = block.hiddenLayers;
            newBlock.maxError = block.maxError;
            newBlock.maxEpochs = block.maxEpochs;
            newBlock.learningRate = block.learningRate;
            newBlock.trainingPath = block.trainingPath;
            newBlock.threshold = block.threshold;
            return newBlock;
        }
    }

    class Builder<T> implements javax.visrec.ml.model.ModelBuilder<BinaryClassifier<T>> {

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

        public Builder<T> trainingPath(Path trainingPath) {
            block.trainingPath = trainingPath;
            return this;
        }
        
        public Builder<T> threshold(float  threshold) {
            block.threshold = threshold;
            return this;
        }
        
        

        public NeuralNetBinaryClassifier.BuildingBlock<T> getBuildingBlock() {
            return block;
        }

        public BinaryClassifier<T> build() throws ModelCreationException {
            return ServiceProvider.current().getClassifierFactoryService().createNeuralNetBinaryClassifier(block);
        }
    }
}
