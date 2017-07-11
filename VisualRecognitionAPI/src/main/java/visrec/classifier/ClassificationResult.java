package visrec.classifier;

/**
 *
 * TODO: map of classes and scores/confidences
 * http://openimaj.org/apidocs/org/openimaj/experiment/evaluation/classification/BasicClassificationResult.html
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ClassificationResult<T> {
    private T classLabel;
    private float score;

    public ClassificationResult(T classLabel, float score) {
        this.classLabel = classLabel;
        this.score = score;
    }

    public T getClassLabel() {
        return classLabel;
    }

    public float getScore() {
        return score;
    }
    
    
    
}
