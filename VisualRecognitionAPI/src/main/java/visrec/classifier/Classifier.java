package visrec.classifier;

/**
 * Generic classifier interface, that all classifiers should implement.
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 *
 * @param <INPUT_TYPE> type of input instance
 * @param <CLASS_TYPE> type of class for classification result CLASS TYPE can be boolean for binary classifier, enum for small number of predefined classes or string for maximum flexibility and big number of classes
 * @see ClassificationResults
 * 
 * 
 *   Interface ClassificationResults<CLASS>
 *   http://openimaj.org/apidocs/org/openimaj/experiment/evaluation/classification/ClassificationResult.html
 *   http://openimaj.org/apidocs/org/openimaj/experiment/evaluation/classification/BasicClassificationResult.html
 */
@FunctionalInterface
public interface Classifier <INPUT_TYPE, CLASS_TYPE> {   // string or boolean, or enum?

    /**
     * Classify specified instance and return classification results
     * 
     * @param instance some instance to classify
     * @return classification results for the specified instance
     */
    public ClassificationResults classify(INPUT_TYPE instance);

//    public static class Result {
//
//        private String classLabel;
//        private float score; // confidence
//
//        public Result(String classLabel, float score) {
//            this.classLabel = classLabel;
//            this.score = score;
//        }
//
//        public String getClassLabel() {
//            return classLabel;
//        }
//
//        public float getScore() {
//            return score;
//        }
//
//        public void setLabel(String label) {
//            this.classLabel = label;
//        }
//
//        public void setScore(float score) {
//            this.score = score;
//        }
//
//        @Override
//        public String toString() {
//            return "ClassificationResult{" + "classLabel=" + classLabel + ", score=" + score + '}';
//        }
//    }
            
}