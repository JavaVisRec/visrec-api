package visrec.classifier;

/**
 * This class represents a single classification result with class label and 
 * corresponding score (confidence level).
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ClassificationResult {
    private String classLabel;
    private float score; // confidence

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

    public void setLabel(String label) {
        this.classLabel = label;
    }

    public void setScore(float score) {
        this.score = score;
    }
    
    @Override
    public String toString() {
        return "ClassificationResult{" + "classLabel=" + classLabel + ", score=" + score + '}';
    }        
}