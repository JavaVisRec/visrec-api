package javax.visrec.ml.data;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Not used at the moment but all data sets should extend thos it will be easier to create custm data set
 * @author Zoran Sevarac
 * @param <E> type of data set elements
 */
public class AbstractDataSet<E> implements DataSet<E> {

    protected List<E> elements;

    protected AbstractDataSet(List<E> elements) {
        this.elements = elements;
    }
    
    @Override
    public DataSet<E> add(E item) {
        elements.add(item);
        return this;
    }

    @Override
    public DataSet<E> addAll(DataSet<E> dataSet) {
        elements.addAll(dataSet.getItems());
        return this;
    }

    @Override
    public E get(int index) {
        return elements.get(index);
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public <E> DataSet<E>[] split(int parts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataSet<E>[] split(int parts, Random rnd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataSet<E>[] split(double part) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataSet<E>[] split(double... parts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataSet<E>[] split(Random rnd, double... parts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataSet<E> shuffle() {
        Collections.shuffle(elements);
        return this;
    }

    @Override
    public DataSet<E> shuffle(Random rnd) {
        Collections.shuffle(elements, rnd);
        return this;
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    @Override
    public Collection<E> getItems() {
        return elements;
    }
    
}
