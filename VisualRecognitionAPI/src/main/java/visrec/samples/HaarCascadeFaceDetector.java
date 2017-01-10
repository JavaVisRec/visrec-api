package visrec.samples;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openimaj.image.FImage;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import visrec.detection.AbstractDetector;
import visrec.detection.Detector;
import visrec.util.ImageFactory;
import visrec.util.MBFImageFactory;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class HaarCascadeFaceDetector extends AbstractDetector<MBFImage> {
    FaceDetector<DetectedFace, FImage> faceDetector;

    public HaarCascadeFaceDetector() {
        faceDetector = new HaarCascadeDetector(40);
    }
        
    public HaarCascadeFaceDetector(FaceDetector<DetectedFace, FImage> faceDetector) {
        this.faceDetector = faceDetector;
    }
         
    @Override
    public List detect(MBFImage image) {
            // MBFImage image = ImageUtilities.readMBF(imageFile);

            List<DetectedFace> faces = faceDetector.detectFaces(image.flatten());
            
            return faces;


    }

}
