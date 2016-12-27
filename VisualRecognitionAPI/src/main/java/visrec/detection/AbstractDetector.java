package visrec.detection;

import java.util.List;
import visrec.classifier.Classifier;
import visrec.util.BoundingBox;

/**
 * missing image to search in
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public abstract class AbstractDetector<IMAGE_CLASS> implements Detector<IMAGE_CLASS> {

    Classifier<IMAGE_CLASS, Boolean> classifier;

    public AbstractDetector(Classifier<IMAGE_CLASS, Boolean> classifier) {
        this.classifier = classifier;
    }    
    
    @Override
    public abstract List<BoundingBox> detect(IMAGE_CLASS image);
    
}
