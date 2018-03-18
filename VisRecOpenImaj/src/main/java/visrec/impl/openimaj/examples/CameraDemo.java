package visrec.impl.openimaj.examples;

import java.util.List;
import java.util.Map;
import javax.visrec.detection.ObjectDetector;
import javax.visrec.util.BoundingBox;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.math.geometry.shape.Rectangle;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.VideoDisplayListener;
import org.openimaj.video.capture.VideoCapture;
import org.openimaj.video.capture.VideoCaptureException;

/**
 * http://openimaj.org/tutorial/finding-faces.html
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class CameraDemo {

    public static void main(String[] args) throws VideoCaptureException {
        VideoCapture vc = new VideoCapture(500, 400);
        VideoDisplay<MBFImage> vd = VideoDisplay.createVideoDisplay(vc);

        vd.addVideoListener(new VideoDisplayListener<MBFImage>() {
            @Override
            public void beforeUpdate(MBFImage frame) {
//                FaceDetector<DetectedFace,FImage> fd = new HaarCascadeDetector(40);
//                List<DetectedFace> faces = fd.detectFaces(Transforms.calculateIntensity(frame));
//
//                for( DetectedFace face : faces ) {
//                    frame.drawShape(face.getBounds(), RGBColour.RED);
//                }

                ObjectDetector<MBFImage> faceDetector = new HaarCascadeFaceDetector();
                Map<String, List<BoundingBox>> results = faceDetector.detectObject(frame);

                for (List<BoundingBox> bboxes : results.values()) {
                    for (BoundingBox bbox : bboxes) {
                        frame.drawShape(new Rectangle(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight()), RGBColour.RED);
                    }
                }
            }

            public void afterUpdate(VideoDisplay<MBFImage> display) {
            }
        });
    }
}
