package visrec.examples;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openimaj.image.FImage;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import org.openimaj.math.geometry.shape.Rectangle;
import visrec.detection.AbstractDetector;
import visrec.detection.Detector;
import visrec.util.BoundingBox;
import visrec.util.ImageFactory;
import visrec.util.MBFImageFactory;

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
    public List<BoundingBox> detect(MBFImage image) {
            // MBFImage image = ImageUtilities.readMBF(imageFile);

           List<DetectedFace> faces = faceDetector.detectFaces(image.flatten());
           List<BoundingBox> bboxes = new ArrayList<>();
           
           for(DetectedFace face : faces) {
               Rectangle bounds = face.getBounds();
               BoundingBox bbox = new BoundingBox((int)bounds.x, (int)bounds.y, (int)bounds.width, (int)bounds.height);
               bbox.setLabel("face");
               bbox.setScore(face.getConfidence());
               bboxes.add(bbox);
           }
           
            // convert detected faces BoundingBox 
            return bboxes;
    }

    @Override
    public List<BoundingBox> detect(File file) throws IOException {
        MBFImage image = imageFactory.getImage(file);
        return detect(image);
    }

    @Override
    public List<BoundingBox> detect(URL url) throws IOException {
        MBFImage image = imageFactory.getImage(url);
        return detect(image);
    }

    @Override
    public List<BoundingBox> detect(InputStream inStream) throws IOException {
        MBFImage image = imageFactory.getImage(inStream);
        return detect(image);
    }

}
