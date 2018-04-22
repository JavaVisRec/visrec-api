package javax.visrec.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Implementation is used ONLY in the unit tests.
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
@Image(ExampleImageFactory.ExampleImage.class)
public class ExampleImageFactory implements ImageFactory<ExampleImageFactory.ExampleImage> {

    @Override
    public ExampleImage getImage(File file) throws IOException {
        return new ExampleImage();
    }

    @Override
    public ExampleImage getImage(URL file) throws IOException {
        return new ExampleImage();
    }

    @Override
    public ExampleImage getImage(InputStream file) throws IOException {
        return new ExampleImage();
    }

    static class ExampleImage {

        private ExampleImage() {
            // Class can not be instantiated outside ExampleImageFactory
        }
    }
}
