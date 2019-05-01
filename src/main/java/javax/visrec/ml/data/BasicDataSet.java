package javax.visrec.ml.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Basic implementation of {@link DataSet} interface
 *
 * @author Zoran Sevarac
 */
public class BasicDataSet<E> implements DataSet<E>{

    private List<E> items;
    private Column[] columns;

    /**
     * Creates an instance of {@link BasicDataSet}
     * @param cols columns of the data set.
     */
    public BasicDataSet(Column[] cols) {
        this.columns = cols;
        items = new ArrayList<>();
    }

    /**
     * Creates an instance of {@link BasicDataSet}
     * @param elements items of the data set.
     */
    public BasicDataSet(List<E> elements) {
        this.items = elements;
    }

    @Override
    public DataSet<E> add(E item) {
        items.add(item);
        return this;
    }

    @Override
    public DataSet<E>  addAll(DataSet<E> items) {
        this.items.addAll(this.items);
        return this;
    }

    @Override
    public E get(int index) {
        return items.get(index);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public DataSet<E>[]  split(int parts) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public DataSet<E>[]  split(int parts, Random rnd) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public DataSet<E>[] split(double... parts) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public DataSet[] split(Random rnd, double... parts) {
        throw new UnsupportedOperationException("not implemented");
    }


    // this shuld go to utilities class or default method?
    public String[] getTargetLabels() {
        List<String> targetLabels = Arrays.asList(columns).stream()
                        .filter((col) -> col.isTarget())
                        .map((col) -> col.getName() )
                        .collect(Collectors.toList());
        return targetLabels.toArray(new String[0]);
    }

//    public void setColumnNames(String[] labels) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }

    @Override
    public List<E> getItems() {
        return items;
    }



}
