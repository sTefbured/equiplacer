package com.kotikov.equiplacer.jbinarytreepanel.listener;

import com.kotikov.equiplacer.jbinarytreepanel.JBinaryTreePanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class JBinaryTreePanelMouseWheelListener implements MouseWheelListener {
    private static final Logger LOGGER = LogManager.getLogger(JBinaryTreePanelMouseWheelListener.class);
    private static final int DELTA_STEP = 10;

    private final JBinaryTreePanel binaryTreePanel;

    public JBinaryTreePanelMouseWheelListener(JBinaryTreePanel binaryTreePanel) {
        this.binaryTreePanel = binaryTreePanel;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int newDelta = binaryTreePanel.getDelta();
        if (e.getWheelRotation() > 0) {
            newDelta += DELTA_STEP;
        } else {
            newDelta -= DELTA_STEP;
        }
        binaryTreePanel.setDelta(newDelta);

        LOGGER.debug("Grid delta: {}", binaryTreePanel.getDelta());
        binaryTreePanel.repaint();
    }
}
