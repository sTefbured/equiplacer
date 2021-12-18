package com.kotikov.equiplacer.client.desktop.view.menu;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        super();
        add(new FileMenu());
        add(new UtilsMenu());
    }
}
