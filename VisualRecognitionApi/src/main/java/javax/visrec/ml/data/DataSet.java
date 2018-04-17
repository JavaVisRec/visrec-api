package javax.visrec.ml.data;

/**
 * @author Zoran
 */
public interface DataSet<ITEM_TYPE extends DataSetItem> extends Iterable<ITEM_TYPE> {

    public void add(ITEM_TYPE item);

    public void addAll(DataSet<ITEM_TYPE> items);

    public ITEM_TYPE get(int index);

    public void clear();

    public boolean isEmpty();

    public int size();

    public DataSet[] split(int parts);

    public DataSet[] split(int... parts);

    public String[] getOutputLabels();

    public void setColumnNames(String[] labels);

    public void shuffle();

}
