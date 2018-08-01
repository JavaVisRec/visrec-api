package visrec.impl.deepnetts.examples;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import javax.visrec.AbstractImageClassifier;
import javax.visrec.detection.ObjectDetector;
import javax.visrec.detection.SimpleObjectDetector;
import visrec.impl.deepnetts.DeepNettsImageClassifier;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class DukeDetectorDemo {

    public static void main(String[] args) throws IOException {

        Properties prop = new Properties();
        // provide data set properties
        prop.setProperty("imageWidth", "64");
        prop.setProperty("imageHeight", "64");
        prop.setProperty("labelsFile", "/home/zoran/datasets/DukeSet/labels.txt");
        prop.setProperty("trainingFile", "/home/zoran/datasets/DukeSet/train.txt");

        // provide model properties
        //prop.setProperty("networkArch", "architecture.json");
        prop.setProperty("modelFile", "dukeDetector.dnet");  // save trained model in file at the end

        // provide training properties
        prop.setProperty("maxError", "0.03");
        prop.setProperty("learningRate", "0.01");

        AbstractImageClassifier imageClassifier = new DeepNettsImageClassifier();
        imageClassifier.build(prop);

        System.out.println("Done building image classifier.");

        System.out.println("Detecting dukes...");
        ObjectDetector logoDetector = new SimpleObjectDetector(imageClassifier); // should we use scanning? Test it a bit further
        Map<String, Float> results = logoDetector.detectObject(new File("/home/zoran/datasets/DukeSet/TestDukeDetection.png"));

        // imageClassifier.classify(file)
        System.out.println(results);
        System.out.println("Done.");

        // show image and frame and outline results
    }

}
