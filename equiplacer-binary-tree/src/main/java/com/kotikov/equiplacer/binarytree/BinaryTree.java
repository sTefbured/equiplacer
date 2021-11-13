package com.kotikov.equiplacer.binarytree;

import java.util.Collection;
import java.util.Iterator;

public class BinaryTree<T> implements Collection<T> {
    private Node rootNode;
    private int size;

    public BinaryTree() {

    }

    public BinaryTree(T rootNodeData) {
        this.rootNode = new Node(rootNodeData);
        size = 1;
    }

    public Node getRootNode() {
        return rootNode;
    }

    @Override
    public int size() {
        return size;
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

    public class Node {
        private Node leftChild;
        private Node rightChild;
        private Node parent;
        private T data;

        private Node(T data) {
            this.data = data;
        }

        private Node(T data, Node parent) {
            this(data);
            this.parent = parent;
        }

        public Node addLeftChild(T child) {
            leftChild = new Node(child, this);
            size++;
            return leftChild;
        }

        public Node addRightChild(T child) {
            rightChild = new Node(child, this);
            size++;
            return rightChild;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public Node getParent() {
            return parent;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
