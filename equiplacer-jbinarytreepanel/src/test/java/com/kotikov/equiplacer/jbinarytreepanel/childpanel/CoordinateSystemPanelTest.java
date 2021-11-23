package com.kotikov.equiplacer.jbinarytreepanel.childpanel;

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
            frame.add(new CoordinateSystemPanel("Year", "Age", 30));
            frame.setVisible(true);
        });
    }
}