package visrec.examples;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.openimaj.image.FImage;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import org.openimaj.math.geometry.shape.Rectangle;
import visrec.classifier.ClassificationResults;
import visrec.detection.Detector;
import visrec.util.BoundingBox;
import visrec.util.ImageFactory;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class HaarCascadeFaceDetector implements Detector<MBFImage> {
    FaceDetector<DetectedFace, FImage> faceDetector;
    ImageFactory<MBFImage> imageFactory;

    public HaarCascadeFaceDetector() {
        faceDetector = new HaarCascadeDetector(40);
    }
        
    public HaarCascadeFaceDetector(FaceDetector<DetectedFace, FImage> faceDetector) {
        this.faceDetector = faceDetector;
    }
         
    @Override
    public ClassificationResults detect(MBFImage image) {
            // MBFImage image = ImageUtilities.readMBF(imageFile);

           List<DetectedFace> faces = faceDetector.detectFaces(image.flatten());
           ClassificationResults results = new ClassificationResults();
           
           for(DetectedFace face : faces) {
               Rectangle bounds = face.getBounds();
               BoundingBox bbox = new BoundingBox("face", face.getConfidence(), (int)bounds.x, (int)bounds.y, (int)bounds.width, (int)bounds.height);
               //bbox.setLabel("face");
               //bbox.setScore(face.getConfidence());
               results.add(bbox);
           }
           
            return results;
    }

    //@Override
    public ClassificationResults detect(File file) throws IOException {
        MBFImage image = imageFactory.getImage(file);
        return detect(image);
    }
//
//    @Override
//    public ClassificationResults detect(InputStream inStream) throws IOException {
//        MBFImage image = imageFactory.getImage(inStream);
//        return detect(image);
//    }

}
