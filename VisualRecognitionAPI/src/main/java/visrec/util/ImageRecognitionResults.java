package visrec.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class holds a collection of image recognition results.
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ImageRecognitionResults implements Iterable<ImageRecognitionResult> {

    List<ImageRecognitionResult> results;

    public ImageRecognitionResults() {
        results = new ArrayList<>();
    }
    
    public void add(String clazz, double score) {
        ImageRecognitionResult r = new ImageRecognitionResult(clazz, score);
        results.add(r);
    }
             
    @Override
    public String toString() {
        return "ImageRecognitionResults{" + "results=" + results + '}';
    }

    @Override
    public Iterator<ImageRecognitionResult> iterator() {
        return results.iterator();
    }

    public void add(ImageRecognitionResult r) {
              results.add(r);
    }
    
    
    
}
