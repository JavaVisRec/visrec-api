package visrec.impl.openimaj.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.visrec.util.ImageFactory;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class MBFImageFactory implements ImageFactory<MBFImage> {

    @Override
    public MBFImage getImage(File file) throws IOException {
        return ImageUtilities.readMBF(file);
    }

    @Override
    public MBFImage getImage(URL url) throws IOException {
        return ImageUtilities.readMBF(url);
    }

    @Override
    public MBFImage getImage(InputStream inputStream) throws IOException {
        return ImageUtilities.readMBF(inputStream);
    }

}
