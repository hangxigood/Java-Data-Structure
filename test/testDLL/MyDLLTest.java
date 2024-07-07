/**
 * 
 */
package testDLL;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.NoSuchElementException;
import implementations.MyDLL;
import utilities.Iterator;

/**
 * @author Hangxi Xiang
 *
 */
public class MyDLLTest {
	
	private MyDLL<Integer> list;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new MyDLL<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		list = null;
	}

	/**
	 * Test method for {@link implementations.MyDLL#MyDLL()}.
	 */
	@Test
	public void testMyDLL() {
		assertNotNull("List should not be null", list);
        assertEquals("New list should be empty", 0, list.size());
	}

	/**
	 * Test method for {@link implementations.MyDLL#size()}.
	 */
	@Test
	public void testSize() {
        assertEquals("Initial size of list should be 0", 0, list.size());
        list.add(1);
        assertEquals("Size of list should be 1 after adding one element", 1, list.size());
    }

	/**
	 * Test method for {@link implementations.MyDLL#clear()}.
	 */
	@Test
	public void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals("List should be empty after clear", 0, list.size());
    }
	/**
	 * Test method for {@link implementations.MyDLL#add(int, java.lang.Object)}.
	 * Test for IndexOutOfBoundsException by accessing an invalid index.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIntE() {
        list.add(0, 10);
        assertEquals("Element 10 should be added at index 0", Integer.valueOf(10), list.get(0));
        list.add(1, 20);
        assertEquals("Element 20 should be added at index 1", Integer.valueOf(20), list.get(1));
        list.add(3, 40);  // Should throw
    }

    /**
     * Test for NullPointerException when adding a null element to the list.
     */
    @Test(expected = NullPointerException.class)
    public void testAdd_NullElement() {
        list.add(null);
    }

	/**
	 * Test method for {@link implementations.MyDLL#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE() {
        assertTrue("Add should return true", list.add(5));
        assertFalse("List should not be empty after add", list.isEmpty());
    }
	/**
	 * Test method for {@link implementations.MyDLL#addAll(utilities.ListADT)}.
	 * Test for NullPointerException when adding a list of elements and the list is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testAddAll() {
        MyDLL<Integer> newList = new MyDLL<>();
        newList.add(1);
        newList.add(2);
        assertTrue("addAll should return true when elements are added", list.addAll(newList));
        assertEquals("List should have 2 elements after addAll", 2, list.size());
        list.addAll(null);
    }
    
	/**
	 * Test method for {@link implementations.MyDLL#get(int)}.
	 * Test for IndexOutOfBoundsException by getting an element from an empty list.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet() {
        list.add(1);
        assertEquals("Get should return the first element", Integer.valueOf(1), list.get(0));
        list.get(1);  // Should throw
    }

	/**
	 * Test method for {@link implementations.MyDLL#remove(int)}.
	 * Test for IndexOutOfBoundsException by removing an element using an invalid index.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveInt() {
        list.add(1);
        list.add(2);
        assertEquals("Remove should return the removed element", Integer.valueOf(1), list.remove(0));
        assertEquals("Size should be 1 after remove", 1, list.size());
        list.remove(1);  // Should throw
    }

    /**
     * Test for NullPointerException by removing a null element from the list.
     */
    @Test(expected = NullPointerException.class)
    public void testRemove_NullElement() {
        list.remove(null);
    }

	/**
	 * Test method for {@link implementations.MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveE() {
        list.add(1);
        list.remove(Integer.valueOf(1));
        assertTrue("List should be empty after removing the element", list.isEmpty());
    }

	/**
	 * Test method for {@link implementations.MyDLL#set(int, java.lang.Object)}.
	 * Test for IndexOutOfBoundsException by setting an element using an invalid index.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSet() {
        list.add(1);
        list.set(0, 2);
        assertEquals("Set should replace the element at index 0", Integer.valueOf(2), list.get(0));
        list.set(2, 3); // Should throw
    }

    /**
     * Test for NullPointerException by setting a null element in the list.
     */
    @Test(expected = NullPointerException.class)
    public void testSet_NullElement() {
        list.add(10);
        list.set(0, null);  // Should throw because null is not a valid element
    }

	/**
	 * Test method for {@link implementations.MyDLL#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
        assertTrue("New list should be empty", list.isEmpty());
        list.add(1);
        assertFalse("List should not be empty after adding elements", list.isEmpty());
    }

	/**
	 * Test method for {@link implementations.MyDLL#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		list.add(1);
        list.add(2);
        list.add(3);
        assertTrue("List should contains 2", list.contains(2));
	}

	/**
	 * Test method for {@link implementations.MyDLL#toArray(E[])}.
	 */
	@Test
	public void testToArrayEArray() {
        list.add(1);
        list.add(2);
        Integer[] array = new Integer[2];
        array = list.toArray(array);
        assertArrayEquals("toArray(E[]) should return an array with all elements", new Integer[]{1, 2}, array);
    }

	/**
	 * Test method for {@link implementations.MyDLL#toArray()}.
	 */
	@Test
	public void testToArray() {
        list.add(1);
        list.add(2);
        Object[] array = list.toArray();
        assertArrayEquals("toArray should return an array with all elements", new Object[]{1, 2}, array);
    }

	/**
	 * Test method for {@link implementations.MyDLL#iterator()}.
	 */
	@Test
	public void testIterator() {
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertTrue("Iterator should have next", it.hasNext());
        assertEquals("Iterator next should return first element", Integer.valueOf(1), it.next());
        assertEquals("Iterator next should return second element", Integer.valueOf(2), it.next());
        assertFalse("Iterator should not have next after last element", it.hasNext());
    }

    /**
     * Test for NoSuchElementException by calling next on an empty iterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void testIterator_NoSuchElement() {
        Iterator<Integer> it = list.iterator();
        it.next();  // Should throw because there are no elements to iterate over
    }

}
