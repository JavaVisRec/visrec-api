package visrec.examples;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class WatsonDeleteClassifier {

    public static void main(String[] args) {
            // this wont work
            VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
            service.setApiKey("9a576187233d8b7918c6165b8f128466a7420c62");
            
            service.deleteClassifier("myClassifier_2041358635");
            
    }
    
    
}
