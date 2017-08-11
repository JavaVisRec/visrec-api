package visrec.recognition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import visrec.classifier.AbstractImageClassifier;
import visrec.classifier.ClassificationResult;
import visrec.classifier.ClassificationResults;
import visrec.util.RecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <IMAGE_CLASS>
 */
public class AbstractRecognizer<IMAGE_CLASS> implements Recognizer<IMAGE_CLASS> {
    
    AbstractImageClassifier<IMAGE_CLASS, RecognitionResult> imageClassifier; 

    public AbstractRecognizer(AbstractImageClassifier<IMAGE_CLASS, RecognitionResult> classifier) {
        this.imageClassifier = classifier;
    }

    @Override
    public ClassificationResults recognize(IMAGE_CLASS image) {
        return imageClassifier.classify(image);
    }

    @Override
    public ClassificationResults recognize(File file) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(file);        
        return recognize(image);
    }

    @Override
    public ClassificationResults recognize(URL url) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(url);        
        return recognize(image);
    }

    @Override
    public ClassificationResults recognize(InputStream inStream) throws IOException {
        IMAGE_CLASS image = imageClassifier.getImageFactory().getImage(inStream);        
        return recognize(image);
    }
    
}