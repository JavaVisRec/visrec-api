package javax.visrec.ml.data;

import java.util.Random;

/**
 *
 * @author Zoran Sevarac
 * @param <E> type of data set elements
 * @since 1.0
 */
public interface DataSet<E> extends Iterable<E> {

    DataSet<E> add(E item);

    DataSet<E> addAll(DataSet<E> items);

    // remove?
    E get(int index);

    void clear();

    boolean isEmpty();

    int size();

    /**
     * Split dataset into specified number of equally sized parts
     * @param parts amount of parts to be returned
     * @return multiple {@link DataSet} in an array.
     */
    DataSet[] split(int parts);

    /**
     * Split dataset into specified number of equally sized parts, using specified random generator
     * @param parts amount of parts to be returned
     * @param rnd random generator
     * @return multiple {@link DataSet} in an array.
     */
    DataSet[] split(int parts, Random rnd);

    
    /**
     * Split data set in two parts, one with size of specified percentage, and other with rest of the data set
     * @param part specified percentage of the first {@link DataSet}
     * @return multiple {@link DataSet} in an array.
     */
    DataSet[] split(double part);
    
    /**
     * Split data set into parts of specified sizes
     * @param parts specific sizes of {@link DataSet}
     * @return array of {@link DataSet}
     */    
    DataSet[] split(double... parts);
    
    /**
     * Split data set into parts of specified sizes using specified random generator
     * @param rnd random generator
     * @param parts specific sizes of {@link DataSet}
     * @return array of {@link DataSet}
     */        
    DataSet[] split(Random rnd, double... parts);    

    
    // split(Splitter splitter) Splitter can be abstract class
    //DataSet[] split(double... parts, Random rnd);
    DataSet shuffle(); // this could be default method

    DataSet shuffle(Random rnd); // this could be default method

//    String[] getOutputLabels(); 
//
//    void setColumnNames(String[] labels);
    // this can be move to interface DataSet
    public static class Column {

        String name;
        ColumnType columnType;
        boolean isTarget;

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
