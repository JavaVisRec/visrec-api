import java.util.ArrayList;
import java.util.List;

// TODO: complete this object/class

public class PaginationHelper<I> {

    List<I> list;
    int pageSize;
    int pages = 0;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        list = new ArrayList<>(collection);
        pageSize = itemsPerPage;

        // We can calculate total pages once and store as it won't change
        pages = (int) Math.ceil(1.0 * list.size() / itemsPerPage);
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return list.size();
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return pages;
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        if (pageIndex >= pages)
            return -1;

        if (pageIndex < pages - 1)
            return pageSize;

        return list.size() - pageIndex * pageSize;
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if (itemIndex >= list.size() || itemIndex < 0)
            return -1;

        return (int) Math.floor(1.0 * itemIndex / pageSize);
    }
}
