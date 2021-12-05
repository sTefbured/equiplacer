package com.kotikov.equiplacer.client.desktop.view.panel.tabbedpane;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class CustomTabbedPaneUI extends BasicTabbedPaneUI {
    private static final Color TAB_AREA_BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private static final Color SELECTED_TAB_COLOR = new Color(234, 242, 248);

    @Override
    protected void installDefaults() {
        UIManager.put("TabbedPane.selected", SELECTED_TAB_COLOR);
        super.installDefaults();
        focus = SELECTED_TAB_COLOR;
    }

    @Override
    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
        var graphics2D = (Graphics2D) g;
        var oldColor = graphics2D.getColor();
        graphics2D.setColor(TAB_AREA_BACKGROUND_COLOR);
        graphics2D.fill(graphics2D.getClipBounds());
        graphics2D.setColor(oldColor);
        super.paintTabArea(graphics2D, tabPlacement, selectedIndex);
    }
}
