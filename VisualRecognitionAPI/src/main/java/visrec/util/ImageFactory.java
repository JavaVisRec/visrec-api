package visrec.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * This interface provides a standard way to get/read different(specified) kinds of image from file, url or input stream
 * 
 * What about getting it from the screen, camera or from memory?
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface ImageFactory<IMAGE_CLASS> {

    public IMAGE_CLASS getImage(File file) throws IOException; 
    public IMAGE_CLASS getImage(URL file) throws IOException;
    public IMAGE_CLASS getImage(InputStream file) throws IOException;
        
}
