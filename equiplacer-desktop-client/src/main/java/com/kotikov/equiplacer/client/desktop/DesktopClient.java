package com.kotikov.equiplacer.client.desktop;

import com.kotikov.equiplacer.client.desktop.view.ClientFrame;

import javax.swing.*;

public class DesktopClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var frame = new ClientFrame();
            frame.setVisible(true);
        });
    }
}
