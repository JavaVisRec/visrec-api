package javax.visrec.ml.data;

/**
 *
 * @author Zoran Sevarac
 * @param <T> type which inherits {@link DataSetItem}
 * @since 1.0
 */
public interface DataSet<T extends DataSetItem> extends Iterable<T> {

    void add(T item);

    void addAll(DataSet<T> items);

    T get(int index);

    void clear();

    boolean isEmpty();

    int size();

    DataSet[] split(int parts);

    DataSet[] split(int... parts);

    String[] getOutputLabels();

    void setColumnNames(String[] labels);

    void shuffle();

}
