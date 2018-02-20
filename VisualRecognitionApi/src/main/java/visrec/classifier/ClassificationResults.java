package visrec.classifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * Inspired by
 * http://openimaj.org/apidocs/org/openimaj/experiment/evaluation/classification/BasicClassificationResult.html
 *
 * list of classification results TODO: add BoundingBox somehere here
 *
 * a kind of ResultSet
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ClassificationResults<T extends ClassificationResult> {

    private List<T> results;

    public ClassificationResults() {
        results = new ArrayList();
    }

    public void add(T result) {
        if (result == null) {
            throw new IllegalArgumentException("Result cannot be null!");
        }

        results.add(result);
    }

    public Iterator<T> iterator() {
        return results.iterator();
    }

    public void sort() {
        Collections.sort(results, (T o1, T o2) -> {
            float diff = o1.getScore() - o2.getScore();
            return (int)Math.signum(diff);
        });
    }

    public List<T> getTopKResults(int k) {        
        this.sort();
        if (results.size() <k) return results;
        return results.subList(0, k);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        results.forEach((r) -> {
            sb.append(r).append(System.lineSeparator());
        });
        return sb.toString();
    }

}
