package testArray;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.NoSuchElementException;
import implementations.MyArrayList;
import utilities.Iterator;

/**
 * Test class for {@link implementations.MyArrayList}.
 * @author Hangxi Xiang
 */
public class MyArrayListTest {
    private MyArrayList<Integer> list;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        list = new MyArrayList<>();
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        list = null;
    }

    /**
     * Test method for {@link implementations.MyArrayList#MyArrayList()}.
     * Checks if a new list is empty.
     */
    @Test
    public void testMyArrayList() {
        assertNotNull("List should be initialized", list);
        assertEquals("New list should be empty", 0, list.size());
    }

    /**
     * Test method for {@link implementations.MyArrayList#size()}.
     */
    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
    }

    /**
     * Test method for {@link implementations.MyArrayList#clear()}.
     */
    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals("List should be empty after clear", 0, list.size());
    }

    /**
     * Test method for {@link implementations.MyArrayList#add(int, java.lang.Object)}.
     * Expects to throw IndexOutOfBoundsException if the index is out of bounds.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIntE() {
        list.add(0, 1);
        assertEquals("Element 1 should be at index 0", Integer.valueOf(1), list.get(0));
        list.add(1, 2);
        assertEquals("Element 2 should be at index 1", Integer.valueOf(2), list.get(1));
        list.add(3, 4); // This should throw
    }
   

    /**
     * Test method for {@link implementations.MyArrayList#add(java.lang.Object)}.
     * Expects to throw NullPointerException if the added element is null.
     */
    @Test(expected = NullPointerException.class)
    public void testAddE() {
        assertTrue("Add should return true", list.add(1));
        assertEquals("List should contain the added element", Integer.valueOf(1), list.get(0));
        list.add(null);  // This should throw
    }

    /**
     * Test method for {@link implementations.MyArrayList#addAll(utilities.ListADT)}.
     */
    @Test(expected = NullPointerException.class)
    public void testAddAll() {
        MyArrayList<Integer> newList = new MyArrayList<>();
        newList.add(1);
        newList.add(2);
        list.addAll(newList);
        assertEquals("List should contain all elements from newList", 2, list.size());
        list.addAll(null);  // This should throw
    }

    /**
     * Test method for {@link implementations.MyArrayList#get(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet() {
        list.add(1);
        assertEquals("Should retrieve the correct element", Integer.valueOf(1), list.get(0));
        list.get(1);  // This should throw
    }

    /**
     * Test method for {@link implementations.MyArrayList#remove(int)}.
     */
    @Test
    public void testRemoveInt() {
        list.add(1);
        list.add(2);
        Integer removed = list.remove(0);
        assertEquals("Should return the removed element", Integer.valueOf(1), removed);
        assertEquals("Should remove the element from list", 1, list.size());
    }

    /**
     * Test method for {@link implementations.MyArrayList#remove(java.lang.Object)}.
     * Expects to throw IndexOutOfBoundsException if the index is invalid.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveE() {
        list.add(1);
        list.remove(0);
        assertEquals("List should be empty after removal", 0, list.size());
        list.remove(0);  // This should throw, as list is empty
    }

    /**
     * Test method for {@link implementations.MyArrayList#set(int, java.lang.Object)}.
     * Expects to throw IndexOutOfBoundsException if the index is invalid.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet() {
        list.add(1);
        list.set(0, 2);
        assertEquals("Element at index 0 should be updated to 2", Integer.valueOf(2), list.get(0));
        list.set(1, 1);  // This should throw
    }
   
    /**
     * Test method for {@link implementations.MyArrayList#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        assertTrue("New list should be empty", list.isEmpty());
        list.add(1);
        assertFalse("List should not be empty after add", list.isEmpty());
    }

    /**
     * Test method for {@link implementations.MyArrayList#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        list.add(1);
        assertTrue("List should contain 1", list.contains(1));
        assertFalse("List should not contain 2", list.contains(2));
    }

    /**
     * Test method for {@link implementations.MyArrayList#toArray(E[])}.
     */
    @Test
    public void testToArrayEArray() {
        Integer[] array = new Integer[1];
        list.add(1);
        array = list.toArray(array);
        assertEquals("Array should contain the same elements as list", Integer.valueOf(1), array[0]);
    }

    /**
     * Test method for {@link implementations.MyArrayList#toArray()}.
     */
    @Test
    public void testToArray() {
        list.add(1);
        Object[] array = list.toArray();
        assertEquals("Array should contain the same elements as list", Integer.valueOf(1), array[0]);
    }

    /**
     * Test method for {@link implementations.MyArrayList#iterator()}.
     */
    @Test
    public void testIterator() {
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertTrue("Iterator should have next", it.hasNext());
        assertEquals("Iterator next should return the first element", Integer.valueOf(1), it.next());
        assertFalse("Iterator should not have next after last element", it.hasNext());
    }
    
    /**
     * Test method for {@link implementations.MyArrayList#iterator()}.
     * Expects NoSuchElementException if next() is called without a next element.
     */
    @Test(expected = NoSuchElementException.class)
    public void testIteratorNoElement() {
        Iterator<Integer> it = list.iterator();
        it.next();  // This should throw, as there are no elements
    }
}
