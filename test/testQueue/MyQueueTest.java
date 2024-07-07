/**
 * 
 */
package testQueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.EmptyQueueException;
import implementations.MyQueue;
import utilities.Iterator;

/**
 * @author Hangxi Xiang
 *
 */
public class MyQueueTest {
	private MyQueue<Integer> queue;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		queue = new MyQueue<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		queue = null;
	}

    /**
     * Test method for {@link implementations.MyQueue#MyQueue()}.
     * Verifies that a new queue is empty.
     */
    @Test
    public void testMyQueue() {
        assertTrue("New queue should be empty", queue.isEmpty());
        assertEquals("New queue should have size 0", 0, queue.size());
    }

    /**
     * Test method for {@link implementations.MyQueue#enqueue(java.lang.Object)}.
     */
    @Test
    public void testEnqueue() {
        queue.enqueue(10);
        assertFalse("Queue should not be empty after enqueue", queue.isEmpty());
        assertEquals("Queue should have one item", 1, queue.size());
    }

    /**
     * Test method for {@link implementations.MyQueue#dequeue()}.
     * Tests both dequeueing and error condition when dequeueing from an empty queue.
     */
    @Test(expected = EmptyQueueException.class)
    public void testDequeue() throws EmptyQueueException {
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals("Dequeue should return first item", Integer.valueOf(20), queue.dequeue());
        assertEquals("Dequeue should return second item", Integer.valueOf(30), queue.dequeue());
        // Test dequeuing from an empty queue, it should throws EmptyQueueException,
        queue.dequeue();
    }

    /**
     * Test method for {@link implementations.MyQueue#peek()}.
     */
    @Test(expected = EmptyQueueException.class)
    public void testPeek() throws EmptyQueueException {
        queue.enqueue(40);
        assertEquals("Peek should return first item without removing it", Integer.valueOf(40), queue.peek());
        assertEquals("Queue size should remain 1 after peek", 1, queue.size());
        queue.dequeue();
        // Test peeking from an empty queue,it should throws EmptyQueueException.
        queue.peek();
    }
    /**
     * Test method for {@link implementations.MyQueue#dequeueAll()}.
     */
    @Test
    public void testDequeueAll() {
        queue.enqueue(50);
        queue.enqueue(60);
        queue.dequeueAll();
        assertTrue("Queue should be empty after dequeueAll", queue.isEmpty());
    }

    /**
     * Test method for {@link implementations.MyQueue#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        assertTrue("New queue should be empty", queue.isEmpty());
        queue.enqueue(70);
        assertFalse("Queue should not be empty after enqueue", queue.isEmpty());
    }

    /**
     * Test method for {@link implementations.MyQueue#equals(utilities.QueueADT)}.
     */
    @Test
    public void testEqualsQueueADTOfE() {
        MyQueue<Integer> anotherQueue = new MyQueue<>();
        queue.enqueue(80);
        anotherQueue.enqueue(80);
        assertTrue("Queues should be equal", queue.equals(anotherQueue));
        anotherQueue.enqueue(90);
        assertFalse("Queues should not be equal after changes", queue.equals(anotherQueue));
    }

    /**
     * Test method for {@link implementations.MyQueue#toArray()}.
     */
    @Test
    public void testToArray() {
        queue.enqueue(100);
        queue.enqueue(110);
        Object[] array = queue.toArray();
        assertArrayEquals("ToArray should return an array of queue elements", new Object[]{100, 110}, array);
    }

    /**
     * Test method for {@link implementations.MyQueue#toArray(E[])}.
     */
    @Test
    public void testToArrayEArray() {
        queue.enqueue(120);
        queue.enqueue(130);
        Integer[] array = new Integer[2];
        array = queue.toArray(array);
        assertArrayEquals("ToArray(E[]) should return an array of queue elements", new Integer[]{120, 130}, array);
    }

    /**
     * Test method for {@link implementations.MyQueue#isFull()}.
     */
    @Test
    public void testIsFull() {
        assertFalse("Queue should never be full", queue.isFull());
    }

    /**
     * Test method for {@link implementations.MyQueue#size()}.
     */
    @Test
    public void testSize() {
        assertEquals("New queue should have size 0", 0, queue.size());
        queue.enqueue(140);
        assertEquals("Queue size should be 1 after enqueue", 1, queue.size());
    }

    /**
     * Test method for {@link implementations.MyQueue#iterator()}.
     */
    @Test
    public void testIterator() {
        queue.enqueue(150);
        queue.enqueue(160);
        Iterator<Integer> it = queue.iterator();
        assertTrue("Iterator should have next", it.hasNext());
        assertEquals("Iterator should return first element", Integer.valueOf(150), it.next());
        assertEquals("Iterator should return second element", Integer.valueOf(160), it.next());
        assertFalse("Iterator should not have more elements", it.hasNext());
    }
}
