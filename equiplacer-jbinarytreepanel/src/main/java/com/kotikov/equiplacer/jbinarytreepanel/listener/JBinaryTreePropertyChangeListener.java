package com.kotikov.equiplacer.jbinarytreepanel.listener;

import com.kotikov.equiplacer.binarytree.BinaryTree;
import com.kotikov.equiplacer.jbinarytreepanel.node.Node;

public interface JBinaryTreePropertyChangeListener<T extends Number> {
    default void treeChanged(TreeChangeProps<T> treeChangeProps) {
    }

    default void deltaChanged(DeltaChangeProps deltaChangeProps) {
    }

    default void offsetChanged(OffsetChangeProps offsetChangeProps) {
    }

    class TreeChangeProps<E extends Number> {
        private final BinaryTree<Node<E>> oldTree;
        private final BinaryTree<Node<E>> newTree;

        public TreeChangeProps(BinaryTree<Node<E>> oldTree, BinaryTree<Node<E>> newTree) {
            this.oldTree = oldTree;
            this.newTree = newTree;
        }

        public BinaryTree<Node<E>> getOldTree() {
            return oldTree;
        }

        public BinaryTree<Node<E>> getNewTree() {
            return newTree;
        }
    }

    class DeltaChangeProps {
        private final int oldDelta;
        private final int newDelta;

        public DeltaChangeProps(int oldDelta, int newDelta) {
            this.oldDelta = oldDelta;
            this.newDelta = newDelta;
        }

        public int getOldDelta() {
            return oldDelta;
        }

        public int getNewDelta() {
            return newDelta;
        }
    }

    class OffsetChangeProps {
        private final int newOffsetX;
        private final int newOffsetY;
        private final int oldOffsetX;
        private final int oldOffsetY;

        public OffsetChangeProps(int newOffsetX, int newOffsetY, int oldOffsetX, int oldOffsetY) {
            this.newOffsetX = newOffsetX;
            this.newOffsetY = newOffsetY;
            this.oldOffsetX = oldOffsetX;
            this.oldOffsetY = oldOffsetY;
        }

        public int getNewOffsetX() {
            return newOffsetX;
        }

        public int getNewOffsetY() {
            return newOffsetY;
        }

        public int getOldOffsetX() {
            return oldOffsetX;
        }

        public int getOldOffsetY() {
            return oldOffsetY;
        }
    }
}
