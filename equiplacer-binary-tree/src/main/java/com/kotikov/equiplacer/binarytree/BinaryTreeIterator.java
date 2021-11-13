package com.kotikov.equiplacer.binarytree;

import java.util.Iterator;

/**
 * BinaryTreeIterator is an implementation of Iterator interface for BinaryTree class
 * @param <T> type of stored data
 */
public class BinaryTreeIterator<T> implements Iterator<T> {
    private final BinaryTree<T> last;

    private BinaryTree<T> current;
    private BinaryTree<T> previous;
    private boolean isNextLeft = true;
    private boolean isMovingFurther = true;
    private boolean hasNext;


    public BinaryTreeIterator(BinaryTree<T> binaryTree) {
        current = binaryTree;
        last = findLast(binaryTree);
        hasNext = current != null;
    }

    private BinaryTree<T> findLast(BinaryTree<T> binaryTree) {
        if (binaryTree.getRightChild() != null) {
            return findLast(binaryTree.getRightChild());
        } else if (binaryTree.getLeftChild() != null) {
            return findLast(binaryTree.getLeftChild());
        }
        return binaryTree;
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

    private BinaryTree<T> getCurrent() {
        BinaryTree<T> result = current;
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