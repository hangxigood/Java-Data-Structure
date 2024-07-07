/**
 * 
 */
package implementations;

import exceptions.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

/**
 * A custom implementation of an array list that supports generic types.
 * This class provides basic list operations such as add, remove, and get elements by index.
 * It automatically expands its capacity when needed.
 *
 * @param <E> The type of elements in this list
 * @author Hangxi Xiang
 */
public class MyArrayList<E> implements ListADT<E> {
	
	private static final int DEFAULT_CAPACITY = 10; // Default initial capacity of the array
    private E[] data; // Array to store the list elements
    private int size; // Current number of elements in the list

    /**
     * Constructs an empty list with an initial capacity.
     */
    public MyArrayList() {
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    /**
     * Ensures that the array has enough capacity to add new elements.
     * If the array is full, the capacity is doubled.
     */
    private void ensureCapacity() {
        if (size == data.length) {
            E[] newData = (E[]) new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
		
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (toAdd == null) throw new NullPointerException("Cannot add null element to the list");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        ensureCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = toAdd;
        size++;
        return true;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (toAdd == null) throw new NullPointerException("Cannot add null element to the list");
        ensureCapacity();
        data[size++] = toAdd;
        return true;

	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		// TODO Auto-generated method stub
	    if (toAdd == null) throw new NullPointerException("Cannot add from a null list");
	    int initialSize = size;
	    for (int i = 0; i < toAdd.size(); i++) {
	        add(toAdd.get(i));  // Using `add(E)` ensures capacity checks and array resizing are handled.
	    }
	    return size > initialSize;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return data[index];
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        E element = data[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null; // clear the last element and decrease the size.
        return element;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		// TODO Auto-generated method stub
		if (toRemove == null) throw new NullPointerException("Cannot remove null element from the list");
        for (int i = 0; i < size; i++) {
            if (toRemove.equals(data[i])) {
                return remove(i);
            }
        }
        return null;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (toChange == null) throw new NullPointerException("Cannot set null element in the list");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        E oldElement = data[index];
        data[index] = toChange;
        return oldElement;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		// TODO Auto-generated method stub
		if (toFind == null) throw new NullPointerException("Cannot search for null element in the list");
        for (int i = 0; i < size; i++) {
            if (toFind.equals(data[i])) {
                return true;
            }
        }
        return false;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		// TODO Auto-generated method stub
		if (toHold == null) throw new NullPointerException("Input array cannot be null");
        if (toHold.length < size) {
            toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
        }
        System.arraycopy(data, 0, toHold, 0, size);
        
        // Set all elements beyond 'size' in 'toHold' to null
        if (toHold.length > size) {
        	for (int i = size; i < toHold.length; i++) {
                toHold[i] = null;
            }
        }
        return toHold;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Object[] result = new Object[size];
		System.arraycopy(data, 0, result, 0, size);
	    return result;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<E> {
        private int cursor;

        public ArrayListIterator() {
            this.cursor = 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("No more elements in the list");
            return data[cursor++];
        }

	}
	
}
