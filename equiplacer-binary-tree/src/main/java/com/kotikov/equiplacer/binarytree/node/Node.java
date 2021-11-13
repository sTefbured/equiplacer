package com.kotikov.equiplacer.binarytree.node;

public class Node<T> {
    private Node<T> leftChild;
    private Node<T> rightChild;
    private Node<T> parent;
    private T data;

    public Node() {

    }

    public Node(T data) {
        this.data = data;
    }

    private Node(T data, Node<T> parent) {
        this(data);
        this.parent = parent;
    }

    public Node<T> addLeftChild(T child) {
        leftChild = new Node<>(child, this);
        return leftChild;
    }

    public Node<T> addRightChild(T child) {
        rightChild = new Node<>(child, this);
        return rightChild;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
