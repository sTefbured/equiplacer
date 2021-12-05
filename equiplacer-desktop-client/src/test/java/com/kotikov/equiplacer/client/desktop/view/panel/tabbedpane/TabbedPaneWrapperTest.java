package com.kotikov.equiplacer.client.desktop.view.panel.tabbedpane;

import javax.swing.*;

class TabbedPaneWrapperTest {
    public static void main(String[] args) {
        var frame = new JFrame();
        frame.add(new TabbedPaneWrapper());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}