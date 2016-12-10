package visrec.tasks;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface ImageRecognition<I, C> {
    public C recognizeImage(I image);
}
