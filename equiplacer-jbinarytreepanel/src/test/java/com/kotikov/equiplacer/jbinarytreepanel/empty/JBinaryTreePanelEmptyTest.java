package com.kotikov.equiplacer.jbinarytreepanel.empty;

import com.kotikov.equiplacer.jbinarytreepanel.JBinaryTreePanel;

import javax.swing.*;
import java.awt.*;

class JBinaryTreePanelEmptyTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new GridLayout(1, 1));
            frame.add(new JBinaryTreePanel<Integer>("Year", "Age"));
            frame.setVisible(true);
        });
    }
}