package javax.visrec.ml.data;

import java.util.Random;

/**
 *
 * @author Zoran Sevarac
 * @param <E> tye of data set elements
 * @since 1.0
 */
public interface DataSet<E> extends Iterable<E> {

    public DataSet<E> add(E item);

    public DataSet<E> addAll(DataSet<E> items);

    // remove?
    E get(int index);

    void clear();

    boolean isEmpty();

    int size();

    /**
     * Split dataset into specified number of equaly sized parts
     * @param parts
     * @return 
     */
    DataSet[] split(int parts);

    /**
     * Split dataset into specified number of equaly sized parts, using specified random generator 
     * @param parts
     * @return 
     */
    DataSet[] split(int parts, Random rnd);

    
    /**
     * Split data set in two parts, one with size of specified percantage, and other with rest of the data set 
     * @param part
     * @return 
     */
    DataSet[] split(double part);
    
    /**
     * Split data set into parts of specified sizes
     * @param part
     * @return 
     */    
    DataSet[] split(double... parts);
    
    /**
     * Split data set into parts of specified sizes using specified random generator
     * @param part
     * @return 
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

    public static enum ColumnType {
        REAL, INTEGER, BINARY, STRING;
    }

}
