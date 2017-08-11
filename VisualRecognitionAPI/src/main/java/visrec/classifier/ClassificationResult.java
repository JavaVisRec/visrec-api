package visrec.classifier;

/**
 * This class represents a single classification result.
 * 
  * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ClassificationResult {
    private final String classLabel;
    private final float score; // or confidence?

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