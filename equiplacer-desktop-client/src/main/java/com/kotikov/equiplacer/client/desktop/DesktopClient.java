package com.kotikov.equiplacer.client.desktop;

import com.kotikov.equiplacer.client.desktop.context.ApplicationContext;

import javax.swing.*;

public class DesktopClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ApplicationContext::run);
    }
}
