import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PaginationHelperTester {

    @Test
    public void firstTest() {
        PaginationHelper<Character> helper = new PaginationHelper(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);
        assert 2 == helper.pageCount(); //should == 2
        assert 6 == helper.itemCount(); //should == 6
        assert 4 == helper.pageItemCount(0); //should == 4
        Assert.assertEquals(2 , helper.pageItemCount(1));  // last page - should == 2
        Assert.assertEquals( -1 , helper.pageItemCount(2)); // should == -1 since the page is invalid

// pageIndex takes an item index and returns the page that it belongs on
        Assert.assertEquals(1, helper.pageIndex(5)); //should == 1 (zero based index)
        Assert.assertEquals(0, helper.pageIndex(2)); //should == 0
        Assert.assertEquals(-1, helper.pageIndex(20)); //should == -1
        Assert.assertEquals(-1, helper.pageIndex(-10)); //should == -1
    }
}
