package visrec.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class BufferedImageFactory implements ImageFactory<BufferedImage> {

    @Override
    public BufferedImage getImage(File file) throws IOException {
        return ImageIO.read(file);
    }

    @Override
    public BufferedImage getImage(URL url) throws IOException {
        return ImageIO.read(url);
    }

    @Override
    public BufferedImage getImage(InputStream inputStream) throws IOException {
        return ImageIO.read(inputStream);
    }

}
