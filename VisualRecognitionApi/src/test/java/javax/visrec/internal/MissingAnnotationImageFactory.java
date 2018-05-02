package javax.visrec.internal;

import javax.visrec.util.ImageFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Implementation is used ONLY in the unit tests.
 * @author Kevin Berendsen <info@kevinberendsen>
 */
public class MissingAnnotationImageFactory implements ImageFactory<Void> {

    @Override
    public Void getImage(File file) throws IOException {
        return null;
    }

    @Override
    public Void getImage(URL file) throws IOException {
        return null;
    }

    @Override
    public Void getImage(InputStream file) throws IOException {
        return null;
    }
}
