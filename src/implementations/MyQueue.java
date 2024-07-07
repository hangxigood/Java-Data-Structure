package implementations;

import exceptions.EmptyQueueException;
import exceptions.NoSuchElementException;
import utilities.Iterator;
import utilities.QueueADT;

/**
 * A queue implementation using a doubly linked list as the underlying data structure.
 * @param <E> The type of elements held in this queue.
 */
public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> list;

    public MyQueue() {
        list = new MyDLL<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot enqueue null element");
        }
        list.add(toAdd);  // Enqueue at the end of the list
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.remove(0);  // Dequeue from the front of the list
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.get(0);  // Peek at the front of the list
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dequeueAll() {
        list.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(QueueADT<E> that) {
        if (that == null || that.size() != this.size()) {
            return false;
        }
        Iterator<E> otherIt = that.iterator();
        Iterator<E> thisIt = this.iterator();
        while (thisIt.hasNext() && otherIt.hasNext()) {
            if (!thisIt.next().equals(otherIt.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null) {
            throw new NullPointerException("Array provided must not be null");
        }
        return list.toArray(holder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFull() {
        return false;  // Assuming the queue is never full as it's backed by a dynamically resizing list
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<E> {
        private int cursor = 0;

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return cursor < list.size();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the queue");
            }
            return list.get(cursor++);
        }
    }
}
