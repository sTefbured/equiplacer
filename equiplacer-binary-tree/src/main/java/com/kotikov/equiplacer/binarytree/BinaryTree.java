package com.kotikov.equiplacer.binarytree;

import com.kotikov.equiplacer.binarytree.node.Node;

import java.util.Collection;
import java.util.Iterator;

public class BinaryTree<T> implements Collection<T> {
    private Node<T> rootNode;
    private int size;

    public BinaryTree() {

    }

    public BinaryTree(T rootNodeData) {
        this.rootNode = new Node<>(rootNodeData);
        size = 1;
    }

    public Node<T> getRootNode() {
        return rootNode;
    }


    //TODO: implement all the methods below
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
