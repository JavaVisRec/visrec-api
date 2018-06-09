package javax.visrec.ml.data;

/**
 *
 * @author Zoran Sevarac
 * @param <ITEM_TYPE>
 * @since 1.0
 */
public interface DataSet<ITEM_TYPE extends DataSetItem> extends Iterable<ITEM_TYPE> {

    void add(ITEM_TYPE item);

    void addAll(DataSet<ITEM_TYPE> items);

    ITEM_TYPE get(int index);

    void clear();

    boolean isEmpty();

    int size();

    DataSet[] split(int parts);

    DataSet[] split(int... parts);

    String[] getOutputLabels();

    void setColumnNames(String[] labels);

    void shuffle();

}
