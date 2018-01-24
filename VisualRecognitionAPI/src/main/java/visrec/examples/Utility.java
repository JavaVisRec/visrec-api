package visrec.examples;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.math.geometry.shape.Rectangle;

public class Utility {
	public static File getImageFile(String fileName){
    	OpenImajFaceDetectorSample openImajFaceDetectorSample = new OpenImajFaceDetectorSample();
        ClassLoader classLoader = openImajFaceDetectorSample.getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file;
    }
    
    public static void markImageWithRectangle(List<DetectedFace> faces, String inputFileName, File outputFile) throws IOException{
    	BufferedImage image = ImageIO.read(getImageFile(inputFileName));
    	Graphics2D graphics = image.createGraphics();
    	for( DetectedFace face : faces ) {
    		createRectangle(graphics, face, 3, Color.RED);
        }
    	graphics.dispose();
    	ImageIO.write(image, "JPG", outputFile);
    }

	private static void createRectangle(Graphics2D graphics, DetectedFace face, int strokeWidth, Color color) {
		Rectangle rect = face.getBounds();
		graphics.setStroke(new BasicStroke(strokeWidth));
		graphics.setColor(color);
		graphics.drawRoundRect((int)rect.x, (int)rect.y, (int)rect.getHeight(), (int)rect.getWidth(), 5, 5);
	}
}
