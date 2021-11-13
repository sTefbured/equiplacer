package com.kotikov.equiplacer.binarytree;

import java.util.Iterator;

public class BinaryTree<T> implements Iterable<T> {
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

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator<>(this);
    }

    public void clear() {
        Node node = rootNode;
        rootNode = null;
        clearRecursively(node);
        size = 0;
    }

    private void clearRecursively(Node node) {
        if (node == null) {
            return;
        }
        node.data = null;
        node.parent = null;
        if (node.leftChild != null) {
            clearRecursively(node.leftChild);
        }
        if (node.leftChild != null) {
            clearRecursively(node.rightChild);
        }
        node.leftChild = null;
        node.rightChild = null;
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
