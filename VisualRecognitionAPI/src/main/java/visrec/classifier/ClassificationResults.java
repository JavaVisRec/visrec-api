package visrec.classifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * TODO: map of classes and scores/confidences
 * Inspired by 
 * http://openimaj.org/apidocs/org/openimaj/experiment/evaluation/classification/BasicClassificationResult.html
 * 
 * list of classification results
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ClassificationResults {
    private List<ClassificationResult> results;

    public ClassificationResults() {
        results = new ArrayList();
    }
    
    public void add(String classLabel, float score) {
        results.add(new ClassificationResult(classLabel, score));
    }
    
    public void add(ClassificationResult result) {
        if (result == null) throw new IllegalArgumentException("Result cannot be null!");
        
        results.add(result);
    }    
    
    public Iterator<ClassificationResult> iterator() {
        return results.iterator();
    }
    
    public void sort() {
        Collections.sort(results, (ClassificationResult o1, ClassificationResult o2) -> {
            float diff = o1.getScore() - o2.getScore();
            return (int)Math.signum(diff);
        });
    }
    
    public List<ClassificationResult> getTopKResults(int k) {
        this.sort();
        return results.subList(0, k);
    } 
    
}