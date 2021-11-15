package com.kotikov.equiplacer.jbinarytreepanel.node;

import javax.swing.*;
import java.awt.*;

class NodeTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new GridLayout(1, 1));
            frame.add(new Node<>(2, 40));
            frame.setVisible(true);
        });
    }
}