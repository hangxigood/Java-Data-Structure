package implementations;

import exceptions.EmptyStackException;
import exceptions.NoSuchElementException;
import utilities.Iterator;
import utilities.StackADT;

/**
 * A stack implementation using MyArrayList as the underlying data structure.
 * @param <E> The type of elements held in this stack.
 */
public class MyStack<E> implements StackADT<E> {
    private MyArrayList<E> list;

    public MyStack() {
        this.list = new MyArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot push null to the stack");
        }
        list.add(toAdd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
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
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null) {
            throw new NullPointerException("Holder array cannot be null");
        }
        return list.toArray(holder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException("Search element cannot be null");
        }
        return list.contains(toFind);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int search(E toFind) {
        if (toFind == null) {
            return -1;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (toFind.equals(list.get(i))) {
                return list.size() - 1 - i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(StackADT<E> that) {
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
    public int size() {
        return list.size();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }
    
    private class StackIterator implements Iterator<E> {
        private int cursor;

        // Constructor initializes the cursor to point to the last element of the stack
        public StackIterator() {
            this.cursor = size() - 1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return cursor >= 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the stack");
            }
            return list.get(cursor--);  // Return the current element and decrement the cursor
        }
    }

}
