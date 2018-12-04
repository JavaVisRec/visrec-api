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
     * @param item data set item to add to the data set
     * @return current instance of {@link DataSet}
     */
    DataSet<E> add(E item);

    /**
     * Add an existing {@link DataSet} to the current {@link DataSet}
     * @param items existing {@link DataSet}
     * @return current instance of {@link DataSet}
     */
    DataSet<E> addAll(DataSet<E> items);

    /**
     * Get an item from the {@link DataSet}
     * @param index index as {@code int} which corresponds with
     *              the index of the {@link DataSet}
     * @return item from the {@link DataSet}
     */
    E get(int index);

    /**
     * Get a collection of the items in the {@link DataSet}
     * @return {@link Collection}
     */
    Collection<E> getItems();

    /**
     * Clear items of the {@link DataSet}
     */
    void clear();

    /**
     * Determines whether the {@link DataSet} is empty or not.
     * @return {@code true} if the {@link DataSet} is empty, otherwise {@code false}
     */
    boolean isEmpty();

    /**
     * Get the current size of the {@link DataSet}
     * @return size in {@code int}
     */
    int size();

    /**
     * Split dataset into specified number of equally sized parts
     * @param parts amount of parts to be returned
     * @return multiple {@link DataSet} in an array.
     */
    DataSet<E>[] split(int parts);

    /**
     * Split dataset into specified number of equally sized parts, using specified random generator
     * @param parts parts number of parts/subsets to return
     * @param rnd random number generator
     * @return multiple {@link DataSet} in an array.
     */
    DataSet<E>[] split(int parts, Random rnd);

    
    /**
     * Split data set in two parts, one with size of specified percentage, and other with rest of the data set
     * @param part specified percentage of the first {@link DataSet}
     * @return multiple {@link DataSet} in an array.
     */
    DataSet<E>[] split(double part);
    
    /**
     * Split data set into parts of specified sizes
     * @param parts specific sizes of {@link DataSet}
     * @return array of {@link DataSet}
     */    
    DataSet<E>[] split(double... parts);
    
    /**
     * Split data set into parts of specified sizes using specified random generator
     * @param rnd random generator
     * @param parts specific sizes of {@link DataSet}
     * @return array of {@link DataSet}
     */        
    DataSet<E>[] split(Random rnd, double... parts);    

    
    // split(Splitter splitter) Splitter can be abstract class
    //DataSet[] split(double... parts, Random rnd);
    
    /**
     * Shuffles the data set.
     * @return current {@link DataSet}
     */
    DataSet<E> shuffle(); // TODO this could be default method

    /**
     * Shuffles the data set using the specified random number generator.
     * @param rnd random generator
     * @return current {@link DataSet}
     */
    DataSet<E> shuffle(Random rnd); // TODO this could be default method

//    TODO String[] getOutputLabels();
//    TODO void setColumnNames(String[] labels);

    class Column {
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

    enum ColumnType {
        DECIMAL, INTEGER, BINARY, STRING;
    }

}