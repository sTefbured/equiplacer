package com.kotikov.equiplacer.binarytree;

import java.util.Collection;
import java.util.Iterator;

public class BinaryTree<T> implements Collection<T> {
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;
    private BinaryTree<T> parent;
    private T data;
    private int size;

    public BinaryTree() {

    }

    public BinaryTree(T data) {
        this.data = data;
        size = 1;
    }

    private BinaryTree(T data, BinaryTree<T> parent) {
        this(data);
        this.parent = parent;
    }

    public BinaryTree<T> addLeftChild(T child) {
        leftChild = new BinaryTree<>(child, this);
        return leftChild;
    }

    public BinaryTree<T> addRightChild(T child) {
        rightChild = new BinaryTree<>(child, this);
        return rightChild;
    }

    public BinaryTree<T> getLeftChild() {
        return leftChild;
    }

    public BinaryTree<T> getRightChild() {
        return rightChild;
    }

    public BinaryTree<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
