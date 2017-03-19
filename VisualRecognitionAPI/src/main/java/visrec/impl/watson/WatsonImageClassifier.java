package visrec.impl.watson;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import deepnets.imgrec.api.RecognitionResult;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONObject;
import visrec.classifier.ImageClassifier;
import visrec.util.ImageRecognitionResults;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class WatsonImageClassifier extends ImageClassifier<BufferedImage, VisualRecognition> {

    private String apiKey;
    private VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
    private VisualClassifier classifier;
    private String classifierId;

    public WatsonImageClassifier(String apiKey, String classifierId) {
        this.apiKey = apiKey;
        service.setApiKey(apiKey);
        this.classifierId = classifierId;
    }

    private WatsonImageClassifier() {
        
    }
    
    
                 
    @Override
    public void build(Properties properties) {
        
        ClassifierOptions.Builder optionsBuilder = new ClassifierOptions.Builder();
        
        Enumeration en = properties.propertyNames();
        while(en.hasMoreElements()) {
            String key = (String)en.nextElement();
            
            if (key.equals("classifierName")) {
                // if there is no classifier name property geenrate some automaticaly
                String classifierName = properties.getProperty("classifierName"); 
                optionsBuilder.classifierName(classifierName);
            } else {        
                String value = properties.getProperty(key);
                
                if (!key.equals("negative_examples"))
                    optionsBuilder.addClass(key, new File(value));
                else
                    optionsBuilder.negativeExamples(new File(value));
            }
            
            // todo: handle negative examples                        
        }
        
        ClassifierOptions createOptions = optionsBuilder.build();                
        this.classifier = service.createClassifier(createOptions).execute();
    }
    
    // THIS ONE OVERRIDES THE METHOD WITH FILE param not image type
    // we should be able to specify which classifier to use, where that should be specified?
    @Override
    public List<RecognitionResult> classify(File sample) {
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
                .images((File)sample)
                .classifierIds(classifierId)
                .build();
        
        VisualClassification serviceResult = service.classify(options).execute();
        
        JSONObject jsonResult = new JSONObject(serviceResult.toString());
        JSONArray imagesArr = jsonResult.getJSONArray("images");
        JSONObject pbj = imagesArr.getJSONObject(0);
        
        JSONArray classifiersArr = pbj.getJSONArray("classifiers");
        JSONObject classifierObj = classifiersArr.getJSONObject(0);
        JSONArray classes = classifierObj.getJSONArray("classes");

        List<RecognitionResult> results = new ArrayList<>(); // parese serviceResultand fill results         
        
        for(Object classResult : classes) {
            JSONObject r = (JSONObject)classResult;     
            String clazz = r.getString("class");
            double score = r.getDouble("score");
            
            results.add(new RecognitionResult(clazz, score));
            
        }
                       
        return results;
    }


    @Override
    public List<RecognitionResult> classify(BufferedImage sample) {
      // create file from image
//      File tmpImgFile = ImageIO.createImageInputStream(sample);
//       classify(inStream)
      return null;
    }

//

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
            watsonImageClassifier.service.setApiKey(apiKey);    
            return this;
        }
        
        public WatsonImageClassifier build() {
            return watsonImageClassifier;
        }
        
    }


}
