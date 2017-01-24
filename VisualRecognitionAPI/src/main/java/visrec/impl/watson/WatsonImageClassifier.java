package visrec.impl.watson;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import java.io.File;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import visrec.classifier.ImageClassifier;
import visrec.util.ImageRecognitionResults;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class WatsonImageClassifier extends ImageClassifier {

    private String apiKey;
    private VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20); // ovaj bi morao da zamotam u Classifier interface

    public WatsonImageClassifier(String apiKey) {
        this.apiKey = apiKey;
        service.setApiKey(apiKey);
    }
     
    public WatsonImageClassifier(VisualRecognition service) {
        this.service = service;
    }     
    
    
    private WatsonImageClassifier() {
     //  this.apiKey = apiKey;
      //  service.setApiKey("9a576187233d8b7918c6165b8f128466a7420c62");    
      //  setImageFactory(new BufferedImageFactory()); // choose factory based on the specified imageclass
    }
              
    @Override
    public void buildClassifier(Map data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // THIS ONE OVERRIDES THE METHOD WITH FILE param not image type
    @Override
    public ImageRecognitionResults classify(File sample) {
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
                .images((File)sample)
                .build();
        
        VisualClassification serviceResult = service.classify(options).execute();
        
        JSONObject jsonResult = new JSONObject(serviceResult.toString());
        JSONArray imagesArr = jsonResult.getJSONArray("images");
        JSONObject pbj = imagesArr.getJSONObject(0);
        
        JSONArray classifiersArr = pbj.getJSONArray("classifiers");
        JSONObject classifierObj = classifiersArr.getJSONObject(0);
        JSONArray classes = classifierObj.getJSONArray("classes");

        ImageRecognitionResults results = new ImageRecognitionResults(); // parese serviceResultand fill results         
        
        for(Object classResult : classes) {
            JSONObject r = (JSONObject)classResult;     
            String clazz = r.getString("class");
            double score = r.getDouble("score");
            
            results.add(clazz, score);
            
        }
                       
        return results;
    }

    @Override
    public ImageRecognitionResults classify(Object sample) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map classDistribution(Object instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public String classify(Object sample) { // ?????
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    @Override
//    public String classify(IMAGE_TYPE sample) {
//        
//        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
//                .images((File)sample)
//                .build();
//        VisualClassification serviceResult = service.classify(options).execute();
//        
//        ImageRecognitionResults results = new ImageRecognitionResults(); // parese serviceResultand fill results 
//        
//        
//        return serviceResult.toString();       
//        
//    }

    public static class Builder {
    
        WatsonImageClassifier watsonImageClassifier = new WatsonImageClassifier();    
        
        public Builder withApiKey(String apiKey) {
            watsonImageClassifier.apiKey = apiKey;
            watsonImageClassifier.service.setApiKey("9a576187233d8b7918c6165b8f128466a7420c62");    
            return this;
        }
        
        public WatsonImageClassifier build() {
            return watsonImageClassifier;
        }
        
    }


}
