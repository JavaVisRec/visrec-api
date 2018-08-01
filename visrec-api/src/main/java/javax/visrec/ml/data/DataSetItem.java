package javax.visrec.ml.data;

/**
 * Single item in a data set that provides input and (optional) target output.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 * @param <T>
 * @since 1.0
 */
public interface DataSetItem<T> {

    T getInput(); // only data?

    float[] getTargetOutput(); // Used only for supervised learning...
}
