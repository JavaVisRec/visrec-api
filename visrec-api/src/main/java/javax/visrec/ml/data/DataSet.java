package javax.visrec.ml.data;

import java.util.Collection;
import java.util.Random;

/**
 * Generic interface for all type of data sets for machine learning, independent 
 * of type of elements so it has the ability to be reused.
 * 
 * @author Zoran Sevarac
 * @param <E> type of data set elements
 * @since 1.0
 */
public interface DataSet<E> extends Iterable<E> {
    
    /**
     * Adds an element to this data set.
     * 
     * @param item
     * @return 
     */
    DataSet<E> add(E item);

    DataSet<E> addAll(DataSet<E> items);

    E get(int index);
    
    Collection<E> getItems();

    void clear();

    boolean isEmpty();

    int size();

    /**
     * Split dataset into specified number of equally sized parts.
     * 
     * @param parts
     * @return 
     */
    public <E> DataSet<E>[] split(int parts);

    /**
     * Split dataset into specified number of equaly sized parts, using specified random generator 
     * @param parts number of parts/subsets to return
     * @param rnd random number generator
     * @return 
     */
    DataSet<E>[] split(int parts, Random rnd);

    
    /**
     * Split data set in two parts, one with size of specified percantage, and other with rest of the data set 
     * @param part
     * @return 
     */
    DataSet<E>[] split(double part);
    
    /**
     * Split data set into parts of specified sizes which represent 
     * @param parts
     * 
     * @return array of {@link DataSet}
     */    
    DataSet<E>[] split(double... parts);
    
    /**
     * Split data set into parts of specified sizes using specified random generator
     * @param rnd
     * @param parts
     * @return array of {@link DataSet}
     */        
    DataSet<E>[] split(Random rnd, double... parts);    

    
    // split(Splitter splitter) Splitter can be abstract class
    //DataSet[] split(double... parts, Random rnd);
    
    /**
     * Shuffles the data set.
     * 
     * @return 
     */
    DataSet<E> shuffle(); // this could be default method

    /**
     * Shuffles the data set using the specified random number generator.
     * 
     * @param rnd
     * @return 
     */
    DataSet<E> shuffle(Random rnd); // this could be default method

//    String[] getOutputLabels(); 
//
//    void setColumnNames(String[] labels);
    // this can be move to interface DataSet
    public static class Column {
        private final String name;
        private final ColumnType columnType;
        private final boolean isTarget;

        public Column(String name, ColumnType columnType, boolean isTarget) {
            this.name = name;
            this.columnType = columnType;
            this.isTarget = isTarget;
        }

        public String getName() {
            return name;
        }

        public ColumnType getColumnType() {
            return columnType;
        }

        public boolean isTarget() {
            return isTarget;
        }

    }

    public static enum ColumnType {
        DECIMAL, INTEGER, BINARY, STRING;
    }

}
