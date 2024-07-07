/**
 * 
 */
package testStack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import implementations.MyStack;
import utilities.Iterator;
import exceptions.EmptyStackException;

/**
 * @author Hangxi Xiang
 *
 */
public class MyStackTest {
	
	private MyStack<Integer> stack;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		stack = new MyStack<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		stack = null;
	}

	/**
	 * Test method for {@link implementations.MyStack#MyStack()}.
	 */
	@Test
	public void testMyStack() {
        assertTrue("Stack should be empty on initialization", stack.isEmpty());
        assertEquals("Stack size should be 0 on initialization", 0, stack.size());
    }

	/**
	 * Test method for {@link implementations.MyStack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
        stack.push(1);
        assertFalse("Stack should not be empty after push", stack.isEmpty());
        assertEquals("Top of the stack should be the last pushed item", Integer.valueOf(1), stack.peek());
    }

	/**
	 * Test method for {@link implementations.MyStack#pop()}.
	 * Also tests if it throws the EmptyStackException when popping from an empty stack.
	 */
	@Test(expected = EmptyStackException.class)
    public void testPop() {
        stack.push(1);
        stack.push(2);
        assertEquals("Should pop 2", Integer.valueOf(2), stack.pop());
        assertEquals("Should pop 1", Integer.valueOf(1), stack.pop());
        stack.pop(); // This should throw EmptyStackException
    }

	/**
     * Test method for {@link implementations.MyStack#peek()}.
     * Also tests if it throws the EmptyStackException when peeking an empty stack.
     */
    @Test(expected = EmptyStackException.class)
    public void testPeek() {
        stack.push(1);
        assertEquals("Peek should return 1", Integer.valueOf(1), stack.peek());
        stack.pop();
        stack.peek(); // This should throw EmptyStackException
    }

	/**
	 * Test method for {@link implementations.MyStack#clear()}.
	 */
	@Test
	public void testClear() {
        stack.push(1);
        stack.clear();
        assertTrue("Stack should be empty after clear", stack.isEmpty());
    }

	/**
	 * Test method for {@link implementations.MyStack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
        assertTrue("New stack should be empty", stack.isEmpty());
        stack.push(1);
        assertFalse("Stack should not be empty after pushing", stack.isEmpty());
    }

	/**
	 * Test method for {@link implementations.MyStack#toArray()}.
	 */
	@Test
	public void testToArray() {
        stack.push(1);
        stack.push(2);
        Object[] array = stack.toArray();
        assertArrayEquals("toArray should return [1, 2]", new Integer[]{1, 2}, array);
    }

	/**
	 * Test method for {@link implementations.MyStack#toArray(E[])}.
	 */
	@Test
	public void testToArrayEArray() {
        stack.push(1);
        stack.push(2);
        Integer[] holder = new Integer[2];
        holder = stack.toArray(holder);
        assertArrayEquals("toArray(E[]) should return [1, 2]", new Integer[]{1, 2}, holder);
    }

	/**
	 * Test method for {@link implementations.MyStack#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
        stack.push(1);
        assertTrue("Stack should contain 1", stack.contains(1));
        assertFalse("Stack should not contain 2", stack.contains(2));
    }
	/**
	 * Test method for {@link implementations.MyStack#search(java.lang.Object)}.
	 */
	@Test
	public void testSearch() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals("Search should find 3 at position 0", 0, stack.search(3));
        assertEquals("Search should find 1 at position 2", 2, stack.search(1));
        assertEquals("Search should not find 4", -1, stack.search(4));
    }

	/**
	 * Test method for {@link implementations.MyStack#equals(utilities.StackADT)}.
	 */
	@Test
	public void testEqualsStackADTOfE() {
        MyStack<Integer> anotherStack = new MyStack<>();
        anotherStack.push(1);
        stack.push(1);
        assertTrue("Stacks should be equal", stack.equals(anotherStack));
        anotherStack.push(2);
        assertFalse("Stacks should not be equal after modifying one", stack.equals(anotherStack));
    }

	/**
	 * Test method for {@link implementations.MyStack#size()}.
	 */
	@Test
	public void testSize() {
        assertEquals("New stack should have size 0", 0, stack.size());
        stack.push(1);
        assertEquals("Stack size should be 1 after pushing one item", 1, stack.size());
    }

	/**
	 * Test method for {@link implementations.MyStack#iterator()}.
	 */
	@Test
	public void testIterator() {
        stack.push(1);
        stack.push(2);
        Iterator<Integer> it = stack.iterator();
        assertTrue("Iterator should have next", it.hasNext());
        assertEquals("Iterator should return 2 first", Integer.valueOf(2), it.next());
        assertEquals("Iterator should return 1 next", Integer.valueOf(1), it.next());
        assertFalse("Iterator should have no more elements", it.hasNext());
    }

}
