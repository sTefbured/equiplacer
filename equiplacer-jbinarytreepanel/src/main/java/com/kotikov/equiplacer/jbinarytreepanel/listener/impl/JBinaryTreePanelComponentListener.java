package com.kotikov.equiplacer.jbinarytreepanel.listener.impl;

import com.kotikov.equiplacer.jbinarytreepanel.JBinaryTreePanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static com.kotikov.equiplacer.jbinarytreepanel.util.JBinaryTreePanelConstants.COORDINATE_SYSTEM_PADDING;

public class JBinaryTreePanelComponentListener extends ComponentAdapter {
    private final JBinaryTreePanel<? extends Number> panel;

    public JBinaryTreePanelComponentListener(JBinaryTreePanel<? extends Number> panel) {
        this.panel = panel;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        var coordinateSystemPanel = panel.getCoordinateSystemPanel();
        var newWidth = panel.getWidth() - 2 * COORDINATE_SYSTEM_PADDING;
        var newHeight = panel.getHeight() - 2 * COORDINATE_SYSTEM_PADDING;
        coordinateSystemPanel.setSize(newWidth, newHeight);
        panel.revalidate();
    }
}
