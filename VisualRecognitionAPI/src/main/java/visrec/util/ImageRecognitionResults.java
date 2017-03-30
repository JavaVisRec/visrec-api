package visrec.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class holds a collection of image recognition results.
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ImageRecognitionResults implements Iterable<RecognitionResult> {

    private List<RecognitionResult> results;

    public ImageRecognitionResults() {
        results = new ArrayList<>();
    }
    
    public void add(String clazz, double score) {
        RecognitionResult r = new RecognitionResult(clazz, score);
        results.add(r);
    }
    
    public void add(RecognitionResult r) {
              results.add(r);
    }    
             
    @Override
    public String toString() {
        return "ImageRecognitionResults{" + "results=" + results + '}';
    }

    @Override
    public Iterator<RecognitionResult> iterator() {
        return results.iterator();
    }


    
    
    
}
