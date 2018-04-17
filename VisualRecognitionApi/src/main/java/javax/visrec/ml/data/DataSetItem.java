package javax.visrec.ml.data;

/**
 * Single item in a data set that provides input and (optional) target output.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface DataSetItem<T> {

    public T getInput(); // only data?

    public float[] getTargetOutput(); // Used only for supervised learning...
}
