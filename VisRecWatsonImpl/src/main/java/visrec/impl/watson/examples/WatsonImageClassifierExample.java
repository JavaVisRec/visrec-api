package visrec.impl.watson.examples;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import javax.visrec.AbstractImageClassifier;
import visrec.impl.watson.WatsonImageClassifier;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class WatsonImageClassifierExample {

    public static void main(String[] args) throws IOException {

        // BUILDING A CUSTOM IMAGE CLASSIFIER
        // create watson image classifier using constructor with api key
        AbstractImageClassifier imageClassifier = new WatsonImageClassifier("apiKey", "classifierId");

        Properties prop = new Properties();
        prop.setProperty("classifierName", "myClassifier");        // this can be optional
        prop.setProperty("bear", "/home/zoran/animals/bear.zip");   // [className => zipped image files] pairs
        prop.setProperty("deer", "/home/zoran/animals/deer.zip");
        prop.setProperty("duck", "/home/zoran/animals/duck.zip");
        prop.setProperty("turtle", "/home/zoran/animals/turtle.zip");

        imageClassifier.build(prop);

        // USING IMAGE CLASSIFIER
        // classify image, and get results
        Map<String, Float> results = imageClassifier.classify(new File("people.jpg")); // todo: additional classification options?
        System.out.println(results);
    }

}
