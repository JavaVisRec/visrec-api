package javax.visrec.ml.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Basic implementation of {@link DataSet} interface.
 * Provides a list of data set items with column info.
 * 
 * @param <E> Type of elements in data set
 */
public class BasicDataSet<E> implements DataSet<E> {

   /**
    * List of data set items in this data set.
    */
    protected List<E> items; 
    
    /**
     * List of data set columns. Each column provides info about it's name, type.
     */
    private List<Column> columns;

    protected BasicDataSet() {
       items = new ArrayList<>();
    }
    
    /**
     * Creates an instance of {@link BasicDataSet}
     * @param cols columns of the data set.
     */
    public BasicDataSet(Column... cols) {
        this.columns = new ArrayList();
        Arrays.stream(cols).forEach(col->columns.add(col));        
        items = new ArrayList<>();
    }
    
    public BasicDataSet(String... cols) {
        this.columns = new ArrayList();
        Arrays.stream(cols).forEach(col->columns.add(new Column(col)));        
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
    public List<Column> getColumns() {
        return columns;
    }
    
    public void setColumnNames(String[] columnNames) {
        for(int i=0; i<columns.size(); i++) {
             columns.get(i).setName(columnNames[i]);
        }   
    }

    public String[] getColumnNames() {
        String[] colNames = new String[columns.size()];
        for(int i=0; i<columns.size(); i++) {
             colNames[i] = columns.get(i).getName();
        }        
        return colNames;
    }
    
    public void setAsTargetColumns(int... targetIdxs) {
        // reset all cureent target columns
        columns.stream().forEach( c->c.setAsTarget(false));
                        
        for(int idx : targetIdxs) {
            columns.get(idx).setAsTarget(true);
        }
    }
    
    public void setAsTargetColumns(String... targetColNames) {
         columns.stream().forEach( c->c.setAsTarget(false));
         
         columns.stream().forEach(col-> {
                                            for(String name : targetColNames)  {
                                                if (col.getName().equals(name)) col.setAsTarget(true);                                           
                                            }
                                        });
    } 
    
    public String[] getTargetColumnsNames() {
        List<String> targetLabels = columns.stream()
                        .filter((col) -> col.isTarget())
                        .map((col) -> col.getName() )
                        .collect(Collectors.toList());
        return targetLabels.toArray(new String[0]);
    }    
   

    // remove?
    @Override
    public DataSet<E>[] split(double... parts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }


}