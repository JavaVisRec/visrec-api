package visrec.detection;

import java.awt.image.BufferedImage;
import java.util.List;
import visrec.classifier.Classifier;
import visrec.util.BoundingBox;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class SimpleImageDetector extends AbstractDetector<BufferedImage>  {

    public SimpleImageDetector(Classifier<BufferedImage, Boolean> classifier) {
        super(classifier);
    }

    @Override
    public List<BoundingBox> detect(BufferedImage image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
