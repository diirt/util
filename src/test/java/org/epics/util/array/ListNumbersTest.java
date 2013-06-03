/**
 * Copyright (C) 2012 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.util.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author carcassi
 */
public class ListNumbersTest {
    
    @Test
    public void sortedView1() {
        ArrayDouble values = new ArrayDouble(5,3,1,4,2,0);
        SortedListView sortedView = ListNumbers.sortedView(values);
        assertThat(values, equalTo(new ArrayDouble(5,3,1,4,2,0)));
        assertThat(sortedView, equalTo((ListNumber) new ArrayDouble(0,1,2,3,4,5)));
        assertThat(sortedView.getIndexes(), equalTo((ListInt) new ArrayInt(5,2,4,1,3,0)));
    }
    
    @Test
    public void sortedView2() {
        ArrayDouble values = new ArrayDouble(5,3,1,4,2,0);
        ArrayInt indexes = new ArrayInt(0,3,1,4,2,5);
        SortedListView sortedView = ListNumbers.sortedView(values, indexes);
        assertThat(values, equalTo(new ArrayDouble(5,3,1,4,2,0)));
        assertThat(sortedView, equalTo((ListNumber) new ArrayDouble(5,4,3,2,1,0)));
        assertThat(sortedView.getIndexes(), equalTo((ListInt) new ArrayInt(0,3,1,4,2,5)));
    }
    
    @Test
    public void sortedView3() {
        ArrayDouble values = new ArrayDouble(-1.7178013239620846, 0.5200744839822301, 0.638091980352644, 0.093683130487196, -1.2967630810250952, 0.7040257444802407, -0.4166241363846508, 2.9610862677876244, 0.03636268292097817, -0.35530274977371445);
        SortedListView sortedView = ListNumbers.sortedView(values);
        assertThat(sortedView, equalTo((ListNumber) new ArrayDouble(-1.7178013239620846, -1.2967630810250952, -0.4166241363846508, -0.35530274977371445, 0.03636268292097817, 0.093683130487196, 0.5200744839822301, 0.638091980352644, 0.7040257444802407, 2.9610862677876244)));
    }
    
    @Test
    public void sortedView4() {
        ArrayDouble values = new ArrayDouble(0,1,2,4,3,5);
        SortedListView sortedView = ListNumbers.sortedView(values);
        assertThat(values, equalTo(new ArrayDouble(0,1,2,4,3,5)));
        assertThat(sortedView, equalTo((ListNumber) new ArrayDouble(0,1,2,3,4,5)));
        assertThat(sortedView.getIndexes(), equalTo((ListInt) new ArrayInt(0,1,2,4,3,5)));
    }
    
    @Test
    public void binarySearchValueOrLower1() {
        ListNumber values = new ArrayDouble(1,2,3,3,4,5,5,6,7,8,10);
        assertThat(ListNumbers.binarySearchValueOrLower(values, 1), equalTo(0));
        assertThat(ListNumbers.binarySearchValueOrLower(values, 10), equalTo(10));
        assertThat(ListNumbers.binarySearchValueOrLower(values, 2), equalTo(1));
        assertThat(ListNumbers.binarySearchValueOrLower(values, 3), equalTo(2));
        assertThat(ListNumbers.binarySearchValueOrLower(values, 5), equalTo(5));
        assertThat(ListNumbers.binarySearchValueOrLower(values, 9), equalTo(9));
        assertThat(ListNumbers.binarySearchValueOrLower(values, 2.5), equalTo(1));
        assertThat(ListNumbers.binarySearchValueOrLower(values, 0.5), equalTo(0));
        assertThat(ListNumbers.binarySearchValueOrLower(values, 10), equalTo(10));
    }
    
    @Test
    public void binarySearchValueOrLower2() {
        ListNumber values = new ArrayDouble(1,2,2,2,2,2,2,2,2,2,3);
        assertThat(ListNumbers.binarySearchValueOrLower(values, 2), equalTo(1));
   }
    
    @Test
    public void binarySearchValueOrHigher1() {
        ListNumber values = new ArrayDouble(1,2,3,3,4,5,5,6,7,8,10);
        assertThat(ListNumbers.binarySearchValueOrHigher(values, 1), equalTo(0));
        assertThat(ListNumbers.binarySearchValueOrHigher(values, 10), equalTo(10));
        assertThat(ListNumbers.binarySearchValueOrHigher(values, 2), equalTo(1));
        assertThat(ListNumbers.binarySearchValueOrHigher(values, 3), equalTo(3));
        assertThat(ListNumbers.binarySearchValueOrHigher(values, 5), equalTo(6));
        assertThat(ListNumbers.binarySearchValueOrHigher(values, 9), equalTo(10));
        assertThat(ListNumbers.binarySearchValueOrHigher(values, 2.5), equalTo(2));
    }
    
    @Test
    public void binarySearchValueOrHigher2() {
        ListNumber values = new ArrayDouble(1,2,2,2,2,2,2,2,2,2,3);
        assertThat(ListNumbers.binarySearchValueOrHigher(values, 2), equalTo(9));
   }
    
    @Test
    public void linearRange1() throws Exception {
        ListNumber list = ListNumbers.linearListFromRange(0, 1000, 101);
        assertThat(list.getDouble(0), equalTo(0.0));
        assertThat(list.getDouble(35), equalTo(350.0));
        assertThat(list.getDouble(50), equalTo(500.0));
        assertThat(list.getDouble(100), equalTo(1000.0));
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void linearRange2() throws Exception {
        ListNumber list = ListNumbers.linearListFromRange(0, 1000, 100);
        list.getDouble(-1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void linearRange3() throws Exception {
        ListNumber list = ListNumbers.linearListFromRange(0, 1000, 100);
        list.getDouble(1000);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void linearRange4() throws Exception {
        ListNumber list = ListNumbers.linearListFromRange(0, 1000, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void linearRange5() throws Exception {
        ListNumber list = ListNumbers.linearListFromRange(0, 1000, -10);
    }
    
    @Test
    public void linearRange6() throws Exception {
        ListNumber list = ListNumbers.linearListFromRange(1000, 0, 101);
        assertThat(list.getDouble(0), equalTo(1000.0));
        assertThat(list.getDouble(35), equalTo(650.0));
        assertThat(list.getDouble(50), equalTo(500.0));
        assertThat(list.getDouble(100), equalTo(0.0));
    }
    
    @Test
    public void linearList1() throws Exception {
        ListNumber list = ListNumbers.linearList(0, 10, 101);
        assertThat(list.getDouble(0), equalTo(0.0));
        assertThat(list.getDouble(35), equalTo(350.0));
        assertThat(list.getDouble(50), equalTo(500.0));
        assertThat(list.getDouble(100), equalTo(1000.0));
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void linearList2() throws Exception {
        ListNumber list = ListNumbers.linearList(0, 10, 101);
        list.getDouble(-1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void linearList3() throws Exception {
        ListNumber list = ListNumbers.linearList(0, 10, 101);
        list.getDouble(1000);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void linearList4() throws Exception {
        ListNumber list = ListNumbers.linearList(0, 10, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void linearList5() throws Exception {
        ListNumber list = ListNumbers.linearList(0, 10, -10);
    }
    
    @Test
    public void linearList6() throws Exception {
        ListNumber list = ListNumbers.linearList(1000, -10, 101);
        assertThat(list.getDouble(0), equalTo(1000.0));
        assertThat(list.getDouble(35), equalTo(650.0));
        assertThat(list.getDouble(50), equalTo(500.0));
        assertThat(list.getDouble(100), equalTo(0.0));
    }
}