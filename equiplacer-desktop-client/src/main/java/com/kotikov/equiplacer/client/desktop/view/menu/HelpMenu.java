package com.kotikov.equiplacer.client.desktop.view.menu;

import javax.swing.*;

public class HelpMenu extends JMenu {
    private static final String MENU_TITLE = "Help";
    private static final String HELP_ITEM_TITLE = MENU_TITLE;
    private static final String ABOUT_ITEM_TITLE = "About";

    public HelpMenu() {
        super(MENU_TITLE);
        addHelpItem();
        addAboutItem();
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
