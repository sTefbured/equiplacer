package com.kotikov.equiplacer.binarytree;

import java.util.Iterator;

/**
 * BinaryTreeIterator is an implementation of Iterator interface for BinaryTree class.
 * It iterates through a tree using NLR tree traversal.
 *
 * @param <T> type of stored data
 */
@Deprecated
public class BinaryTreeIterator<T> implements Iterator<BinaryTree<T>.Node> {
    private final BinaryTree<T>.Node last;

    private BinaryTree<T>.Node current;
    private BinaryTree<T>.Node previous;
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

    @Override
    public boolean hasNext() {
        if (hasNext && current == last) {
            hasNext = false;
            return true;
        }
        return hasNext;
    }

    @Override
    public BinaryTree<T>.Node next() {
        return getCurrent();
    }

    private BinaryTree<T>.Node findLast(BinaryTree<T>.Node rootNode) {
        if (rootNode.getRightChild() != null) {
            return findLast(rootNode.getRightChild());
        } else if (rootNode.getLeftChild() != null) {
            return findLast(rootNode.getLeftChild());
        }
        return rootNode;
    }

    private BinaryTree<T>.Node getCurrent() {
        BinaryTree<T>.Node result = current;
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
        previous = current;
        current = isNextLeft ? current.getLeftChild() : current.getRightChild();
        setIsNextLeft();
        setIsMovingFurther();
    }

    private void setIsMovingFurther() {
        isMovingFurther = current.getLeftChild() != null && current.getLeftChild() != previous;
        isMovingFurther |= current.getRightChild() != null;
        isMovingFurther &= current.getRightChild() != previous;
    }

    private void setIsNextLeft() {
        isNextLeft = (current.getLeftChild() != null) && (current.getLeftChild() != previous);
    }
}