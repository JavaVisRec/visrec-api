package visrec.examples;

import java.io.File;
import java.util.List;
import org.openimaj.image.FImage;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.colour.Transforms;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.VideoDisplayListener;
import org.openimaj.video.capture.VideoCapture;
import org.openimaj.video.capture.VideoCaptureException;
import visrec.classifier.ClassificationResults;
import visrec.detection.Detector;

/**
 * http://openimaj.org/tutorial/finding-faces.html
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

                  Detector<MBFImage> faceDetector = new HaarCascadeFaceDetector();                 
                  ClassificationResults results = faceDetector.detect(frame);
        
                    for(Object result : results.getTopKResults(5)) {
                         frame.drawShape(((DetectedFace)result).getBounds(), RGBColour.RED);
                    }
            }

            public void afterUpdate(VideoDisplay<MBFImage> display) {
            }
        });
    }
}
