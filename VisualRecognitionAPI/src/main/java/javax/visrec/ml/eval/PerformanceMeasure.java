package javax.visrec.ml.eval;

import java.util.HashMap;

/**
 *
 * @author Zoran
 */
public class PerformanceMeasure {
    public final static String MSE          = "MSE";
    public final static String ACCURACY     = "Accuracy";
    public final static String PRECISION    = "Precision";
    public final static String RECALL       = "Recall";
    public final static String F1SCORE      = "F1Score";
    
    private final HashMap<String, Float> values = new HashMap();
    
    public float get(String key) {
        return values.get(key);
    }
    
    public void set(String key, float value) {
        values.put(key, value);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        values.entrySet().stream().forEach((e) ->  sb.append(e.getKey() + ": "+e.getValue() + System.lineSeparator()) );
                
        return sb.toString();
    }
    
}
