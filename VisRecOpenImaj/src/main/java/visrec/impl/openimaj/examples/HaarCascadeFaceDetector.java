package visrec.impl.openimaj.examples;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.visrec.detection.ObjectDetector;
import javax.visrec.util.BoundingBox;
import javax.visrec.util.ImageFactory;
import org.openimaj.image.FImage;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import org.openimaj.math.geometry.shape.Rectangle;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class HaarCascadeFaceDetector implements ObjectDetector<MBFImage> {

    FaceDetector<DetectedFace, FImage> faceDetector;
    ImageFactory<MBFImage> imageFactory;

    public HaarCascadeFaceDetector() {
        faceDetector = new HaarCascadeDetector(40);
    }

    public HaarCascadeFaceDetector(FaceDetector<DetectedFace, FImage> faceDetector) {
        this.faceDetector = faceDetector;
    }

    @Override
    public Map<String, List<BoundingBox>> detectObject(MBFImage image) {
        // MBFImage image = ImageUtilities.readMBF(imageFile);

        List<DetectedFace> faces = faceDetector.detectFaces(image.flatten());
        Map<String, List<BoundingBox>> results = new HashMap();

        List<BoundingBox> bboxes = new ArrayList();
        for (DetectedFace face : faces) {
            Rectangle bounds = face.getBounds();
            BoundingBox bbox = new BoundingBox("face", face.getConfidence(), (int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
            bboxes.add(bbox);
            //bbox.setLabel("face");
            //bbox.setScore(face.getConfidence());
        }

        results.put("face", bboxes); // bbox

        return results;
    }

    //@Override
    public Map<String, List<BoundingBox>> detect(File file) throws IOException {
        MBFImage image = imageFactory.getImage(file);
        return detectObject(image);
    }
//
//    @Override
//    public ClassificationResults detectObject(InputStream inStream) throws IOException {
//        MBFImage image = imageFactory.getImage(inStream);
//        return detectObject(image);
//    }

}
