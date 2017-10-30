package visrec.util;

/**
 *  Result<T> T moze d abude ClassificationResult ili RecognitionResult ili BoundingBox  - i to da bude u tipu
 * 
 * Moye da nasledi Classifier Result i ima polje bounding box
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
    public class RecognitionResult {
        String classLabel;
        double score;

        public RecognitionResult(String classLabel, double score) {
            this.classLabel = classLabel;
            this.score = score;
        }

        public String getClassLabel() {
            return classLabel;
        }

        public double getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Result{" + "classLabel=" + classLabel + ", score=" + score + '}';
        }
                
    }