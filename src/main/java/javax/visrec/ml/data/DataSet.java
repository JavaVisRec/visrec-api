package javax.visrec.ml.data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Generic interface for all data sets for machine learning, independent of type of elements.
 *
 * @author Zoran Sevarac
 * @param <E> type of data set elements
 * @since 1.0
 */
public interface DataSet<E> extends Iterable<E> {

    // TODO: add stream for filtering elements in data set

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
     * @param index index as {@code int} which corresponds with
     *              the index of the {@link DataSet}
     * @return item from the {@link DataSet}
     */
    default E get(int index) {
        return getItems().get(index);
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

    /**
     * Split dataset into specified number of equally sized parts.
     *
     * @param numParts number of parts to be returned
     * @return multiple {@link DataSet} in an array.
     */
    DataSet<E>[] split(int numParts);

    /**
     * Split dataset into specified number of equally sized parts, using specified random generator.
     * @param numParts number of parts/subsets to return
     * @param rnd random number generator
     * @return multiple {@link DataSet} in an array.
     */
    DataSet<E>[] split(int numParts, Random rnd);

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
    DataSet<E>[] split(Random rnd, double... parts);


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

//    TODO String[] getOutputLabels();
//    TODO void setColumnNames(String[] labels);

    class Column {
        private final String name;
        private final ColumnType type;
        private final boolean isTarget;

        public Column(String name, ColumnType type, boolean isTarget) {
            this.name = name;
            this.type = type;
            this.isTarget = isTarget;
        }

        public String getName() {
            return name;
        }

        public ColumnType getType() {
            return type;
        }

        public boolean isTarget() {
            return isTarget;
        }
    }

    enum ColumnType {
        DECIMAL, INTEGER, BINARY, STRING;
    }

}