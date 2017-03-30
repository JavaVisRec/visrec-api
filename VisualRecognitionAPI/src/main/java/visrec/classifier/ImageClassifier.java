package visrec.classifier;

import deepnets.imgrec.api.DnRecognitionResult;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import visrec.util.BufferedImageFactory;
import visrec.util.ImageFactory;
import visrec.util.RecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <IMAGE_CLASS>
 */
public abstract class ImageClassifier<IMAGE_CLASS, MODEL_CLASS> implements Classifier<IMAGE_CLASS, List<RecognitionResult>> {

    private ImageFactory<IMAGE_CLASS> imageFactory; // get image factory for the cpecified image class   
    private MODEL_CLASS model;

    public ImageClassifier() {
      // instantiate image factory whuch coresponds to specified IMAGE_CLASS
     // imageFactory = new BufferedImageFactory();
    }
    
    public ImageFactory<IMAGE_CLASS> getImageFactory() {
        return imageFactory;
    }
       
    @Override
    public abstract List<RecognitionResult> classify(IMAGE_CLASS sample);

    @Override
    public abstract void build(Properties prop);
      
    public List<RecognitionResult> classify(File file) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(file);
        return classify(image);            
    }

    public List<RecognitionResult> classify(URL url) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(url);
         return classify(image);            
    }

    public List<RecognitionResult> classify(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageFactory.getImage(inStream);
         return classify(image);       
    }

    public void setImageFactory(ImageFactory<IMAGE_CLASS> imageFactory) {
        this.imageFactory = imageFactory; 
    }

    public MODEL_CLASS getModel() {
        return model;
    }
    
    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }

}
