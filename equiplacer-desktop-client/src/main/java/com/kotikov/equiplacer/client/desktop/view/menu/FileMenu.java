package com.kotikov.equiplacer.client.desktop.view.menu;

import javax.swing.*;

public class FileMenu extends JMenu {
    private static final String MENU_TITLE = "File";
    private static final String NEW_TAB_ITEM_TITLE = "New tab";
    private static final String EXIT_ITEM_TITLE = "Exit";

    public FileMenu() {
        super(MENU_TITLE);
        addNewTabItem();
        addExitItem();
    }

    private void addNewTabItem() {
        var newTabItem = new JMenuItem(NEW_TAB_ITEM_TITLE);
        newTabItem.addActionListener(e -> {
            //TODO: create a new tab
        });
        add(newTabItem);
    }

    private void addExitItem() {
        var exitItem = new JMenuItem(EXIT_ITEM_TITLE);
        exitItem.addActionListener(e -> {
            System.exit(0);
        });
        add(exitItem);
    }
}
