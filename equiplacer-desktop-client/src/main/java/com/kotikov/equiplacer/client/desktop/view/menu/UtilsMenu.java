package com.kotikov.equiplacer.client.desktop.view.menu;

import com.kotikov.equiplacer.client.desktop.view.dialog.AuthorizationDialog;

import javax.swing.*;

public class UtilsMenu extends JMenu {
    private static final String MENU_TITLE = "Utils";
    private static final String DEVELOPER_ACCESS_ITEM_TITLE = "Developer access";
    private static final String HELP_ITEM_TITLE = "Help";
    private static final String ABOUT_ITEM_TITLE = "About";

    public UtilsMenu() {
        super(MENU_TITLE);
        addDeveloperAccessItem();
        addHelpItem();
        addAboutItem();
    }

    private void addDeveloperAccessItem() {
        var developerAccessItem = new JMenuItem(DEVELOPER_ACCESS_ITEM_TITLE);
        developerAccessItem.addActionListener(e -> new AuthorizationDialog().setVisible(true));
        add(developerAccessItem);
    }

    private void addHelpItem() {
        var helpItem = new JMenuItem(HELP_ITEM_TITLE);
        helpItem.addActionListener(e -> {
            //TODO: open help dialog
        });
        add(helpItem);
    }

    private void addAboutItem() {
        var aboutItem = new JMenuItem(ABOUT_ITEM_TITLE);
        aboutItem.addActionListener(e -> {
            //TODO: open about dialog
        });
        add(aboutItem);
    }
}
