package com.kotikov.equiplacer.client.desktop.view;

import com.kotikov.equiplacer.client.desktop.view.menu.MenuBar;
import com.kotikov.equiplacer.client.desktop.view.panel.tabbedpane.TabbedPaneWrapper;

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {
    private static final Dimension MINIMUM_SIZE = new Dimension(400, 400);
    private static final Dimension DEFAULT_SIZE = new Dimension(1000, 520);
    private static final String APPLICATION_TITLE = "Equiplacer";

    public ClientFrame() {
        super(APPLICATION_TITLE);
        setMinimumSize(MINIMUM_SIZE);
        setSize(DEFAULT_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setJMenuBar(new MenuBar());
        add(new TabbedPaneWrapper());
    }
}
