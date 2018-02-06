package visrec.impl.watson.examples;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import java.io.File;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class TestWatson {

    public static void main(String[] args) {
        VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("9a576187233d8b7918c6165b8f128466a7420c62");

        System.out.println("Classify an image");
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
                .images(new File("src/main/resources/Bear.jpg"))
                .build();
        VisualClassification result = service.classify(options).execute();
        System.out.println(result);
    }
}
