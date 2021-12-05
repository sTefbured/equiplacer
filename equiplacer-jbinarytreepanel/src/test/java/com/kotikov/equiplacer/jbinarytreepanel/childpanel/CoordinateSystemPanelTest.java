package com.kotikov.equiplacer.jbinarytreepanel.childpanel;

import com.kotikov.equiplacer.binarytree.BinaryTree;
import com.kotikov.equiplacer.jbinarytreepanel.node.Node;

import javax.swing.*;
import java.awt.*;

class CoordinateSystemPanelTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new GridLayout(1, 1));
            BinaryTree<Node<Integer>> tree = new BinaryTree<>(new Node<>(3, 30));
            tree.getRootNode().addLeftChild(new Node<>(4, 30)).addRightChild(new Node<>(1, 30));
            tree.getRootNode().addRightChild(new Node<>(1, 30));
            frame.add(new CoordinateSystemPanel<>(tree));
            frame.setVisible(true);
        });
    }
}