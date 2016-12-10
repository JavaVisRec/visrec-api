package visrec.tasks;

import visrec.classifier.Classifier;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ImageRecognitionProvider<I, C> implements ImageRecognition <I, C> {
    
    Classifier<I, C> classifier;

    public ImageRecognitionProvider(Classifier<I, C> classifier) {
        this.classifier = classifier;
    }

    @Override
    public C recognizeImage(I image) {
        return classifier.classify(image);
    }
    
    
    
    
}
