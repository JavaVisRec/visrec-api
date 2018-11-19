package javax.visrec.ml.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Implementation of basic data set class
 * 
 * @author zoran
 */
public class BasicDataSet<E> implements DataSet<E>{
        
    List<E> items;
    Column[] columns;

    public BasicDataSet(Column[] cols) {
        this.columns = cols;
        items = new ArrayList<>();
    }

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
    public DataSet[] split(int parts) {
        return new DataSet[0];
    }

    @Override
    public DataSet[] split(int parts, Random rnd) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public DataSet[] split(double part) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public DataSet[] split(double... parts) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public DataSet[] split(Random rnd, double... parts) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public DataSet<E> shuffle() {
        Collections.shuffle(items);
        return this;
    }

    @Override
    public DataSet shuffle(Random rnd) {
        Collections.shuffle(items, rnd);
        return this;
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
    public Collection<E> getItems() {
        return items;
    }
    

    
}
