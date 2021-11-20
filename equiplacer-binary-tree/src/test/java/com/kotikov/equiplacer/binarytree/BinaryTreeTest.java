package com.kotikov.equiplacer.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    private static final int DEFAULT_TREE_SIZE = 21;

    private BinaryTree<Integer> binaryTree1;
    private BinaryTree<Integer> binaryTree2;
    private BinaryTree<Integer> binaryTree3;

    @BeforeEach
    public void init() {
        int[] treeValues = new int[DEFAULT_TREE_SIZE];
        for (int i = 0; i < treeValues.length; i++) {
            treeValues[i] = i;
        }
        binaryTree1 = new BinaryTree<>(treeValues[0]);
        binaryTree1.getRootNode().addLeftChild(treeValues[1]).addLeftChild(treeValues[2]).addLeftChild(treeValues[3])
                .addRightChild(treeValues[4]).getParent().getParent().addRightChild(treeValues[5])
                .getParent().getParent().addRightChild(treeValues[6]).addLeftChild(treeValues[7])
                .addLeftChild(treeValues[8]).addLeftChild(treeValues[9]).getParent().addRightChild(treeValues[10])
                .getParent().getParent().getParent().getParent().getParent().addRightChild(treeValues[11])
                .addRightChild(treeValues[12]).addRightChild(treeValues[13]).addLeftChild(treeValues[14])
                .addLeftChild(treeValues[15]).addLeftChild(treeValues[16]).getParent().getParent()
                .addRightChild(treeValues[17]).addLeftChild(treeValues[18]).getParent().addRightChild(treeValues[19])
                .getParent().getParent().getParent().addRightChild(treeValues[20]);

        binaryTree2 = new BinaryTree<>(treeValues[0]);
        binaryTree2.getRootNode().addLeftChild(treeValues[1]).addLeftChild(treeValues[2]).addRightChild(treeValues[3])
                .addRightChild(treeValues[4]).getParent().getParent().getParent().addRightChild(treeValues[5])
                .getParent().getParent().addRightChild(treeValues[6]).addRightChild(treeValues[7])
                .addLeftChild(treeValues[8]).getParent().addRightChild(treeValues[9]).addRightChild(treeValues[10])
                .addRightChild(treeValues[11]).addRightChild(treeValues[12]).addRightChild(treeValues[13])
                .addLeftChild(treeValues[14]).addLeftChild(treeValues[15]).addLeftChild(treeValues[16])
                .getParent().getParent().addRightChild(treeValues[17]).addLeftChild(treeValues[18]).getParent()
                .addRightChild(treeValues[19]).getParent().getParent().getParent().addRightChild(treeValues[20]);

        binaryTree3 = new BinaryTree<>(treeValues[0]);
        binaryTree3.getRootNode().addLeftChild(treeValues[1]).addLeftChild(treeValues[2]).addLeftChild(treeValues[3])
                .getParent().addRightChild(treeValues[4]).getParent().getParent().addRightChild(treeValues[5])
                .addLeftChild(treeValues[6]).addLeftChild(treeValues[7]).getParent().addRightChild(treeValues[8])
                .getParent().getParent().addRightChild(treeValues[9]).getParent().getParent().getParent()
                .addRightChild(treeValues[10]).addLeftChild(treeValues[11]).addLeftChild(treeValues[12])
                .addLeftChild(treeValues[13]).addLeftChild(treeValues[14]).addLeftChild(treeValues[15])
                .addLeftChild(treeValues[16]).getParent().addRightChild(treeValues[17]).getParent().getParent()
                .addRightChild(treeValues[18]).getParent().getParent().addRightChild(treeValues[19])
                .addLeftChild(treeValues[20]);
    }

    @Test
    @SuppressWarnings("all")
    public void size_empty() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        assertEquals(0, tree.size());
    }

    @Test
    public void size_filled() {
        assertEquals(1, new BinaryTree<>(1).size());
        assertEquals(DEFAULT_TREE_SIZE, binaryTree1.size());
        assertEquals(DEFAULT_TREE_SIZE, binaryTree2.size());
        assertEquals(DEFAULT_TREE_SIZE, binaryTree3.size());
    }

    @Test
    public void node_addChildren_null() {
        BinaryTree<Integer> tree = new BinaryTree<>(1);
        tree.getRootNode().addLeftChild(null);
        assertEquals(2, tree.size());
    }

    @Test
    @SuppressWarnings("all")
    public void clear_empty() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.clear();

        assertEquals(0, tree.size());
    }

    @Test
    @SuppressWarnings("all")
    public void clear_filled() {
        BinaryTree<Integer> singleValueTree = new BinaryTree<>(1);

        singleValueTree.clear();
        binaryTree1.clear();
        binaryTree2.clear();
        binaryTree3.clear();

        assertEquals(0, singleValueTree.size());
        assertEquals(0, binaryTree1.size());
        assertEquals(0, binaryTree2.size());
        assertEquals(0, binaryTree3.size());
    }

    @Test
    public void getDepth() {
        assertEquals(0, binaryTree1.getRootNode().getDepth());
        assertEquals(1, binaryTree1.getRootNode().getLeftChild().getDepth());
        assertEquals(1, binaryTree1.getRootNode().getRightChild().getDepth());
        assertEquals(2, binaryTree1.getRootNode().getLeftChild().getRightChild().getDepth());
        assertEquals(4,
                binaryTree1.getRootNode().getLeftChild().getRightChild().getLeftChild().getLeftChild().getDepth());
    }
}