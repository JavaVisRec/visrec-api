package visrec.recognition;

import deepnets.imgrec.api.DnRecognitionResult;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import visrec.classifier.ImageClassifier;
import visrec.util.RecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class AbstractRecognizer<IMAGE_CLASS> implements Recognizer<IMAGE_CLASS,  RecognitionResult> {
    
    ImageClassifier<IMAGE_CLASS, RecognitionResult> imageClassifier; 

    public AbstractRecognizer(ImageClassifier<IMAGE_CLASS, RecognitionResult> classifier) {
        this.imageClassifier = classifier;
    }

    @Override
    public List<RecognitionResult> recognize(IMAGE_CLASS image) {
        return imageClassifier.classify(image);
    }

    @Override
    public List<RecognitionResult> recognize(File file) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(file);        
        return recognize(image);
    }

    @Override
    public List<RecognitionResult> recognize(URL url) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(url);        
        return recognize(image);
    }

    @Override
    public List<RecognitionResult> recognize(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(inStream);        
        return recognize(image);
    }
    
}
