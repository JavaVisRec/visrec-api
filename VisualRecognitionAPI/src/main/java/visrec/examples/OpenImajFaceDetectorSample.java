package visrec.examples;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class OpenImajFaceDetectorSample {
// http://openimaj.org/tutorial/finding-faces.html
    // http://openimaj.org/tutorial/modules.html
    
    public static void main(String[] args) throws IOException {
        FaceDetector<DetectedFace,FImage> fd = new HaarCascadeDetector(40);
    
        MBFImage image = ImageUtilities.readMBF(new File("people.jpg"));
        List<DetectedFace> faces = fd.detectFaces(image.flatten());

        for( DetectedFace face : faces ) {
            System.out.println(face.getBounds());
        }
    }
}
