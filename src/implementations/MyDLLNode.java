package implementations;

/**
 * Class representing a node in a doubly linked list.
 * @param <E> The type of element stored in this node.
 */
public class MyDLLNode<E> {
    E data;
    MyDLLNode<E> prev;
    MyDLLNode<E> next;

    /**
     * Constructs a new node with specified data, previous node, and next node.
     * @param data The data stored in the node.
     * @param prev The previous node in the list.
     * @param next The next node in the list.
     */
    public MyDLLNode(E data, MyDLLNode<E> prev, MyDLLNode<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
