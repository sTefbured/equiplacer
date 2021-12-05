package com.kotikov.equiplacer.client.desktop.view.menu;

import javax.swing.*;

class MenuBarTest {
    public static void main(String[] args) {
        var frame = new JFrame();
        frame.setJMenuBar(new MenuBar());
        frame.setSize(1000, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}