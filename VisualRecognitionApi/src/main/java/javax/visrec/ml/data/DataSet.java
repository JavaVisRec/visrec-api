package javax.visrec.ml.data;

/**
 * @author Zoran
 */
public interface DataSet<ITEM_TYPE extends DataSetItem> extends Iterable<ITEM_TYPE> {

    public void add(ITEM_TYPE item);

    public void addAll(DataSet<ITEM_TYPE> items);   // provide default impl of this method?

    public ITEM_TYPE get(int index);

    public void clear();

    public boolean isEmpty();

    public int size();

    public DataSet[] split(int parts);

    public DataSet[] split(double... parts);

    public String[] getOutputLabels();

    public void setColumnNames(String[] labels);    //meta data?    // cretae DefaultDataSetImpl or AbstractDataSet

    public void shuffle();
    
    // some method to easily get statistics summary

}
