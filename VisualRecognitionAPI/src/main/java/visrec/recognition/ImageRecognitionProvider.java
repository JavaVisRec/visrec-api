package visrec.recognition;

import java.util.List;
import visrec.classifier.ImageClassifier;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ImageRecognitionProvider<IMAGE, RESULT> implements Recognizer<IMAGE, RESULT> {
    
    ImageClassifier<IMAGE, RESULT> classifier;

    public ImageRecognitionProvider(ImageClassifier<IMAGE, RESULT> classifier) {
        this.classifier = classifier;
    }

    @Override
    public List<RESULT> recognize(IMAGE image) {
        return (List<RESULT>) classifier.classify(image);
    }
    
}
