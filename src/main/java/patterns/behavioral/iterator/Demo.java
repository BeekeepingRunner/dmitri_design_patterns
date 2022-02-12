package patterns.behavioral.iterator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class Node<T>
{
    public T value;
    public Node<T> leftChild;
    public Node<T> rightChild;
    public Node<T> parent;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> leftChild, Node<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;

        leftChild.parent = rightChild.parent = this;
    }
}

class InOrderIterator<T> implements Iterator<T>
{
    private Node<T> current;
    private Node<T> root;
    private boolean yieldedStart;

    public InOrderIterator(Node<T> root) {
        this.root = current = root;
        while (current.leftChild != null)
            current = current.leftChild;
    }

    private boolean hasRightmostParent(Node<T> node) {
        if (node.parent == null)
            return false;
        else {
            return (node == node.parent.leftChild || hasRightmostParent(node.parent));
        }
    }

    @Override
    public boolean hasNext() {
        return current.leftChild != null
                || current.rightChild != null
                || hasRightmostParent(current);
    }

    @Override
    public T next() {

        if (!yieldedStart) {
            yieldedStart = true;
            return current.value;
        }

        if (current.rightChild != null) {
            current = current.rightChild;
            while (current.leftChild != null)
                current = current.leftChild;
            return current.value;
        }
        else {
            Node<T> p = current.parent;
            while (p != null && current == p.rightChild)
            {
                current = p;
                p = p.parent;
            }
            current = p;
            return current.value;
        }
    }
}

class BinaryTree<T> implements Iterable<T>
{
    private Node<T> root;

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator<>(root);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (T item : this)
            action.accept(item);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}

public class Demo {

    public static void main(String[] args) {

        //      1
        //     / \
        //    2   3

        // 213

        final Node<Integer> root = new Node<>(1, new Node<>(2), new Node<>(3));

        final InOrderIterator<Integer> it = new InOrderIterator<>(root);

        while (it.hasNext()) {
            System.out.print("" + it.next() + ",");
        }
        System.out.println();

        final BinaryTree<Integer> tree = new BinaryTree<>(root);
        for (int n : tree)
            System.out.println("" + n + ',');
        System.out.println();
    }
}
