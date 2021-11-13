package com.kotikov.equiplacer.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeIteratorTest {
    private BinaryTree<Integer> binaryTree1;
    private BinaryTree<Integer> binaryTree2;
    private BinaryTree<Integer> binaryTree3;
    private int[] treeValues;

    @BeforeEach
    public void init() {
        treeValues = new int[21];
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
    public void iterator_foreach_test() {
        int i = 0;
        for (Integer value : binaryTree1) {
            assertEquals(treeValues[i], value);
            i++;
        }

        i = 0;
        for (Integer value : binaryTree2) {
            assertEquals(treeValues[i], value);
            i++;
        }

        i = 0;
        for (Integer value : binaryTree3) {
            assertEquals(treeValues[i], value);
            i++;
        }
    }
}