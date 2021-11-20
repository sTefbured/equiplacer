package com.kotikov.equiplacer.jbinarytreepanel.listener;

import com.kotikov.equiplacer.jbinarytreepanel.JBinaryTreePanel;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class JBinaryTreePanelComponentListener extends ComponentAdapter {
    private final JBinaryTreePanel<? extends Number> panel;

    public JBinaryTreePanelComponentListener(JBinaryTreePanel<? extends Number> panel) {
        this.panel = panel;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        for (Component component : panel.getComponents()) {
            component.setSize(panel.getSize());
        }
    }
}
