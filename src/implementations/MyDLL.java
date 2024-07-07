package implementations;

import exceptions.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

/**
 * A doubly linked list implementation of the ListADT interface for generic data types.
 * @param <E> The type of elements held in this list
 * @author Hangxi Xiang
 */
public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException("Cannot add null elements to the list.");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index out of bounds.");

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd, null, null);
        if (index == 0) {
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } else if (index == size) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
            MyDLLNode<E> current = getNode(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
        return true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        return add(size, toAdd);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot add from null collection.");
        int initialSize = size;
        for (int i = 0; i < toAdd.size(); i++) {
            add(toAdd.get(i));
        }
        return size > initialSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds.");
        return getNode(index).data;
    }

    /**
     * {@inheritDoc}
     */
    private MyDLLNode<E> getNode(int index) {
        MyDLLNode<E> current = head;
        if (index < (size / 2)) {
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) current = current.prev;
        }
        return current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds.");
        MyDLLNode<E> toRemove = getNode(index);
        if (toRemove.prev != null) toRemove.prev.next = toRemove.next;
        else head = toRemove.next;

        if (toRemove.next != null) toRemove.next.prev = toRemove.prev;
        else tail = toRemove.prev;

        size--;
        return toRemove.data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException("Cannot remove null elements.");
        MyDLLNode<E> current = head;
        while (current != null && !current.data.equals(toRemove)) {
            current = current.next;
        }
        if (current == null) return null;
        if (current.prev != null) current.prev.next = current.next;
        else head = current.next;

        if (current.next != null) current.next.prev = current.prev;
        else tail = current.prev;

        size--;
        return current.data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Cannot set null elements.");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds.");
        MyDLLNode<E> current = getNode(index);
        E oldData = current.data;
        current.data = toChange;
        return oldData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Cannot check null elements.");
        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.data.equals(toFind)) return true;
            current = current.next;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("Input array cannot be null.");
        if (toHold.length < size) toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            toHold[i++] = current.data;
            current = current.next;
        }
        return toHold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        return array;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private MyDLLNode<E> current = head;
            
            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return current != null;
            }
            
            /**
             * {@inheritDoc}
             */
            @Override
            public E next() {
                if (current == null) throw new NoSuchElementException("No more elements.");
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
