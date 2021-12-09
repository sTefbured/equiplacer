package com.kotikov.equiplacer.jgraphpanel.listener.impl;

import com.kotikov.equiplacer.jgraphpanel.JGraphPanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static com.kotikov.equiplacer.jgraphpanel.util.JGraphPanelConstants.COORDINATE_SYSTEM_PADDING;

public class JGraphPanelComponentListener extends ComponentAdapter {
    private final JGraphPanel<? extends Number> panel;

    public JGraphPanelComponentListener(JGraphPanel<? extends Number> panel) {
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
