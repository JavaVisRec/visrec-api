package visrec.util;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
    public class RecognitionResult {
        String classLabel;
        double score;
        String typeHierarchy;

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