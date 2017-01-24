package visrec.recognition;

import java.util.List;
import visrec.classifier.Classifier;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ImageRecognitionProvider<I, C> implements Recognizer <I, C> {
    
    Classifier<I, C> classifier;

    public ImageRecognitionProvider(Classifier<I, C> classifier) {
        this.classifier = classifier;
    }

    @Override
    public List<C> recognize(I image) {
        return (List<C>) classifier.classify(image);
    }
    
}
