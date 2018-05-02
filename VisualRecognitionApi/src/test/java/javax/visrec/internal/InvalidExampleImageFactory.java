package javax.visrec.internal;

import javax.visrec.util.Image;
import javax.visrec.util.ImageFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Implementation is used ONLY in the unit tests.
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
@Image(InvalidExampleImageFactory.InvalidExampleImage.class)
public class InvalidExampleImageFactory implements ImageFactory<BufferedImage> {

    @Override
    public BufferedImage getImage(File file) throws IOException {
        return null;
    }

    @Override
    public BufferedImage getImage(URL file) throws IOException {
        return null;
    }

    @Override
    public BufferedImage getImage(InputStream file) throws IOException {
        return null;
    }

    static class InvalidExampleImage {

        private InvalidExampleImage() {
            // Class can not be instantiated outside ExampleImageFactory
        }
    }
}
