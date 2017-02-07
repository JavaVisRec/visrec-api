package visrec.impl.watson;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20); // ovaj bi morao da zamotam u Classifier interface
    private  VisualClassifier classifier; // this should be wraped in classfier interface?

    public WatsonImageClassifier(String apiKey) {
        this.apiKey = apiKey;
        service.setApiKey(apiKey);
    }
    
//    public WatsonImageClassifier(String apiKey) {
//        this.apiKey = apiKey;
//        service.setApiKey(apiKey);
//        also use classifierId    
//    }    
     
    public WatsonImageClassifier(VisualRecognition service) {
        this.service = service;
    }     
    
    
    private WatsonImageClassifier() {
     //  this.apiKey = apiKey;
      //  service.setApiKey("9a576187233d8b7918c6165b8f128466a7420c62");    
      //  setImageFactory(new BufferedImageFactory()); // choose factory based on the specified imageclass
    }
              
    @Override
    public void buildClassifier(Properties data) {
        
        String classifierName = "myClassifier";
        // put classes and file paths into the data map
        // maybe create class to take all the parameters
        // maybe create zip files from the specified path and standard dir structure?
                        
        ClassifierOptions createOptions = new ClassifierOptions.Builder().classifierName(classifierName)
                .addClass("bear", new File("/home/zoran/animals/bear.zip"))
                .addClass("deer", new File("/home/zoran/animals/deer.zip"))
                .addClass("duck", new File("/home/zoran/animals/duck.zip"))
                .addClass("turtle", new File("/home/zoran/animals/turtle.zip"))
   //             .negativeExamples(new File("src/test/resources/visual_recognition/negative.zip")) // can you provide negative examples for specific class and is it needed at all? very likely - car is not a bus
                .build();
        
        this.classifier = service.createClassifier(createOptions).execute();
    }
    
    // THIS ONE OVERRIDES THE METHOD WITH FILE param not image type
    // we should be able to specify which classifier to use, where that should be specified?
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
    public ImageRecognitionResults classify(BufferedImage sample) {
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
            watsonImageClassifier.service.setApiKey("9a576187233d8b7918c6165b8f128466a7420c62");    
            return this;
        }
        
        public WatsonImageClassifier build() {
            return watsonImageClassifier;
        }
        
    }


}
