package visrec.classifier;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Example of a trivial classifier that map images to classes / strings
 * We should add some basic type of image classifier from some open source library as an example...
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class SimpleClassifier implements Classifier <BufferedImage, String>{
    
    Map<BufferedImage, String> map;
    
    @Override
    public void buildClassifier(Map<BufferedImage, String> data) {
        this.map = map;
    }

    @Override
    public String classify(BufferedImage instance) {
        return map.get(instance);
    }

    @Override
    public Map<String, Double> classDistribution(BufferedImage instance) {
        Map<String, Double> classDist = new HashMap<>();
        for(Map.Entry<BufferedImage, String> entry : map.entrySet() ) {
            if (entry.getKey().equals(instance))
                classDist.put( entry.getValue() , new Double(1));
            else 
                classDist.put( entry.getValue() , new Double(0));
        }
        return classDist;
    }

    
    
}
