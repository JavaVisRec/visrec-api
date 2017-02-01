package visrec.examples;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
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
public class VisRecFaceDetectorSample {

    public static void main(String[] args) throws IOException {
        
        AbstractDetector<MBFImage> faceDetector = new HaarCascadeFaceDetector();                 
        faceDetector.setImageFactory(new MBFImageFactory()); // this should be automaticly losaded by detector based on the image class
//        ImageFactory<MBFImage> imageFactory = new MBFImageFactory();
//        MBFImage image = imageFactory.getImage(new File("people.jpg"));
        
        List results = faceDetector.detect(new File("people.jpg"));
        
        for(Object result : results) {
            System.out.println( ((DetectedFace)result).getBounds());
        }
   
        // we need to allow detectors recognizers and annotators to get input directly from file, url or stream. Maybe some ImageConsumerInterface ? 
        // we dont want to make people to dig deep into our api and understand internal classes in order to be able to use it (thats most important thing)
        // visual recognition api for mainstream java developer that does not know anything about imaging details or machine learning - make this technlogy available to masses
    }
    
}
