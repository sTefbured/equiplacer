package com.kotikov.equiplacer.jbinarytreepanel;

import javax.swing.*;
import java.awt.*;

class JBinaryTreePanelTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new GridLayout(1, 1));
            JBinaryTreePanel<Integer> binaryTreePanel = new JBinaryTreePanel<>("Year", "Age");
            frame.add(binaryTreePanel);
            frame.setVisible(true);
        });
    }
}