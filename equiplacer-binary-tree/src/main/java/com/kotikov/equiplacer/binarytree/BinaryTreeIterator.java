package com.kotikov.equiplacer.binarytree;

import com.kotikov.equiplacer.binarytree.node.Node;

import java.util.Iterator;

/**
 * BinaryTreeIterator is an implementation of Iterator interface for BinaryTree class
 * @param <T> type of stored data
 */
public class BinaryTreeIterator<T> implements Iterator<T> {
    private final Node<T> last;

    private Node<T> current;
    private Node<T> previous;
    private boolean isNextLeft;
    private boolean isMovingFurther;
    private boolean hasNext;


    public BinaryTreeIterator(BinaryTree<T> binaryTree) {
        current = binaryTree.getRootNode();
        last = findLast(current);
        isNextLeft = true;
        isMovingFurther = true;
        hasNext = current != null;
    }

    private Node<T> findLast(Node<T> rootNode) {
        if (rootNode.getRightChild() != null) {
            return findLast(rootNode.getRightChild());
        } else if (rootNode.getLeftChild() != null) {
            return findLast(rootNode.getLeftChild());
        }
        return rootNode;
    }

    @Override
    public boolean hasNext() {
        if (hasNext && current == last) {
            hasNext = false;
            return true;
        }
        return hasNext;
    }

    @Override
    public T next() {
        return getCurrent().getData();
    }

    private Node<T> getCurrent() {
        Node<T> result = current;
        moveNext();
        return result;
    }

    private void moveNext() {
        if (!hasNext) {
            return;
        }
        while (!isMovingFurther) {
            previous = current;
            current = current.getParent();
            setIsMovingFurther();
        }
        if (isNextLeft) {
            previous = current;
            current = current.getLeftChild();
        } else {
            previous = current;
            current = current.getRightChild();
        }
        setIsNextLeft();
        setIsMovingFurther();
    }

    private void setIsMovingFurther() {
        isMovingFurther = (current.getLeftChild() != null || current.getRightChild() != null)
                && current.getRightChild() != previous
                && (current.getRightChild() != null || current.getLeftChild() != previous);
    }

    private void setIsNextLeft() {
        isNextLeft = (current.getLeftChild() != null) && (current.getLeftChild() != previous);
    }
}