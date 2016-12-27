package visrec.recognition;

import java.util.List;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface Recognizer<IMAGE, RESULT> {
        public List<RESULT> recognize(IMAGE image);
    
}
