package visrec.util;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
    public class ImageRecognitionResult {
        String classLabel;
        double score;
        String typeHierarchy;

        public ImageRecognitionResult(String clazz, double score) {
            this.classLabel = clazz;
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