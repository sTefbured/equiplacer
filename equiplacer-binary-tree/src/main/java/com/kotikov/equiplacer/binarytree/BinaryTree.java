package com.kotikov.equiplacer.binarytree;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Iterator;
import java.util.Objects;

public class BinaryTree<T> implements Iterable<BinaryTree<T>.Node> {
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

    public void setRootNode(T data) {
        clear();
        rootNode = new Node(data);
        size = 1;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<BinaryTree<T>.Node> iterator() {
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(size).append(rootNode).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BinaryTree<?> tree)) {
            return false;
        }
        if (hashCode() != obj.hashCode()) {
            return false;
        }
        return size == tree.size && rootNode.equals(tree.rootNode);
    }

    public class Node {
        private Node leftChild;
        private Node rightChild;
        private Node parent;
        private T data;
        private boolean isDataChanged;
        private int hashCode;

        private Node(T data) {
            this.data = data;
            isDataChanged = true;
        }

        private Node(T data, Node parent) {
            this(data);
            this.parent = parent;
        }

        public int getDepth() {
            if (parent == null) {
                return 0;
            }
            return parent.getDepth() + 1;
        }

        public Node addLeftChild(T child) {
            leftChild = new Node(child, this);
            size++;
            isDataChanged = true;
            return leftChild;
        }

        public Node addRightChild(T child) {
            rightChild = new Node(child, this);
            size++;
            isDataChanged = true;
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

        @Override
        public int hashCode() {
            if (isDataChanged) {
                hashCode = new HashCodeBuilder().append(leftChild)
                        .append(rightChild).append(data).toHashCode();
                isDataChanged = false;
            }
            return hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BinaryTree<?>.Node node)) {
                return false;
            }
            if (hashCode() != obj.hashCode()) {
                return false;
            }
            return Objects.equals(leftChild, node.leftChild)
                    && Objects.equals(rightChild, node.rightChild)
                    && (parent == node.parent || Objects.equals(parent.data, node.parent.data))
                    && Objects.equals(data, node.data);
        }
    }
}