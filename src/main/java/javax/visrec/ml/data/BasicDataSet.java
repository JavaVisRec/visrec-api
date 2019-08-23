package javax.visrec.ml.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Basic implementation of {@link DataSet} interface.
 * Provides a list of data set items with column info.
 * 
 * @param <E> Type of elements in data set
 * @author Zoran Sevarac
 */
public class BasicDataSet<E> implements DataSet<E> {

   /**
    * List of data set items in this data set
    */
    protected List<E> items;  //this should be a data frame map of lists, even better us evalue types!
    private Column[] columns;

    protected BasicDataSet() {
       items = new ArrayList<>();
    }
    
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
    public List<E> getItems() {
        return items;
    }       

        
    @Override
    public String[] getTargetNames() {
        List<String> targetLabels = Arrays.asList(columns).stream()
                        .filter((col) -> col.isTarget())
                        .map((col) -> col.getName() )
                        .collect(Collectors.toList());
        return targetLabels.toArray(new String[0]);
    }

    @Override
    public void setColumnNames(String[] columnNames) {
        for(int i=0; i<columns.length; i++) {
             columns[i] = new DataSet.Column(columnNames[i]);
        }   
    }


    @Override
    public String[] getColumnNames() {
        String[] colNames = new String[columns.length];
        for(int i=0; i<columns.length; i++) {
             colNames[i] = columns[i].getName();
        }        
        return colNames;
    }

    @Override
    public DataSet<E>[] split(double... parts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}