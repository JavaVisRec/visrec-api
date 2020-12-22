package javax.visrec.ml.data;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Generic interface for all data sets for machine learning, independent of type of elements.
 *
 * @author Zoran Sevarac
 * @param <E> type of data set elements
 * @since 1.0
 */
public interface DataSet<E> extends Iterable<E> {

    /**
     * Get a collection of the items in the {@link DataSet}
     * @return {@link Collection}
     */
    List<E> getItems();

    /**
     * Adds an element to this data set.
     *
     * @param item data set item to add to the data set
     * @return current instance of {@link DataSet}
     */
    default DataSet<E> add(E item) {
        Objects.requireNonNull(item, "Null items are not allowed in dataset");
        getItems().add(item);
        return this;
    }

    /**
     * Add an existing {@link DataSet} to the current {@link DataSet}
     * @param dataSet existing {@link DataSet}
     * @return current instance of {@link DataSet}
     */
    default DataSet<E> addAll(DataSet<E> dataSet) {
        Objects.requireNonNull(dataSet, "Dataset is null. Cannot add items from null dataset");
        getItems().addAll(dataSet.getItems());
        return this;
    }

    /**
     * Get an item from the {@link DataSet}
     * @param idx index as {@code int} which corresponds with
     *              the index of the {@link DataSet}
     * @return item from the {@link DataSet}
     */
    default E get(int idx) {
        return getItems().get(idx);
    }

    /**
     * Clear items of the {@link DataSet}
     */
    default  void clear() {
        getItems().clear();
    }
 
    /**
     * Determines whether the {@link DataSet} is empty or not.
     * @return {@code true} if the {@link DataSet} is empty, otherwise {@code false}
     */
    default boolean isEmpty() {
        return getItems().isEmpty();
    }

    /**
     * Get the number of elements in {@link DataSet}
     * @return size in {@code int}
     */
    default int size() {
        return getItems().size();
    }
    
    @Override
    default Iterator<E> iterator() {
        return getItems().iterator();
    }
       
    /**
     * Split data set into specified number of equally sized parts.
     * @param numParts number of parts to be returned
     * @return multiple {@link DataSet} in an array.
     */
    default DataSet<E>[] split(int numParts) {
        double part = 1.0 / (double)numParts;
        double[] parts = new double[numParts];
        
        for (int i=0; i<numParts; i++) {
            parts[i] = part;
        }
        
        return split(parts);
    }

    /**
     * Split data set into specified number of equally sized parts, using specified random generator.
     * @param numParts number of parts/subsets to return
     * @param rnd random number generator
     * @return multiple {@link DataSet} in an array.
     */
    default DataSet<E>[] split(int numParts, Random rnd) {
        double part = 1.0 / (double)numParts;
        double[] parts = new double[numParts];
        
        for (int i=0; i<numParts; i++) {
            parts[i] = part;
        }
        
        return split(rnd, parts);
    }

    /**
     * Split data set in two parts, one with size of specified percentage, and other with rest of the data set
     *
     * @param part specified percentage of the first {@link DataSet}
     * @return multiple {@link DataSet} in an array.
     */
    default DataSet<E>[] split(double part) {
        return split(part, 1-part);
    }

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
    default DataSet<E>[] split(Random rnd, double... parts) {
        shuffle(rnd);
        return split(parts);
    }


    /**
     * Shuffles the data set.
     */
    default void shuffle() {
        Collections.shuffle(getItems());
    }

    /**
     * Shuffles the data set using the specified random number generator.
     * @param rnd random generator
     */
    default void shuffle(Random rnd) {
        Collections.shuffle(getItems(), rnd);
    }
               
    /**
     * Sets the columns of this data set.
     * 
     * @param columns 
     */
    public void setColumns(List<Column> columns);

    /**
     * Returns the columns of the data set.
     * 
     * @return 
     */
    public List<Column> columns();

    
    default public Stream<E> stream() {
        return getItems().stream();
    }

}