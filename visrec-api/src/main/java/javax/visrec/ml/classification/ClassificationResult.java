package javax.visrec.ml.classification;

/**
 * This class represents a single classification result with class label and 
 * corresponding score (confidence level).
 * 
 * @author Zoran Sevarac
 * @since 1.0
 */
public class ClassificationResult {
    private final String classLabel;
    private final float score; // confidence

    public ClassificationResult(String classLabel, float score) {
        this.classLabel = classLabel;
        this.score = score;
    }

    public String getClassLabel() {
        return classLabel;
    }

    public float getScore() {
        return score;
    }

    
    @Override
    public String toString() {
        return "ClassificationResult{" + "classLabel=" + classLabel + ", score=" + score + '}';
    }        
}