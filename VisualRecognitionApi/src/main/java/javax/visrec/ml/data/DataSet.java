package javax.visrec.ml.data;

import java.util.Random;

/**
 *
 * @author Zoran Sevarac
 * @param <E> tye of data set elements
 * @since 1.0
 */
public interface DataSet<E> extends Iterable<E> {

    void add(E item);

    void addAll(DataSet<E> items);

    E get(int index);

    void clear();

    boolean isEmpty();

    int size();

    DataSet[] split(int parts);
    
    DataSet[] split(int parts, Random rnd);

    DataSet[] split(double... parts);
    
    // split(DataSetSplitter splitter)
    
    //DataSet[] split(double... parts, Random rnd);

    DataSet shuffle(); // this could be default method
    
    String[] getOutputLabels(); 

    void setColumnNames(String[] labels);

    

}
