package com.kotikov.equiplacer.jbinarytreepanel.listener;

import com.kotikov.equiplacer.jbinarytreepanel.JBinaryTreePanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class JBinaryTreePanelMouseListener extends MouseAdapter {
    private static final Logger LOGGER = LogManager.getLogger(JBinaryTreePanelMouseListener.class);
    private static final int DELTA_STEP = 10;

    private final JBinaryTreePanel binaryTreePanel;

    private int lastX;
    private int lastY;

    public JBinaryTreePanelMouseListener(JBinaryTreePanel binaryTreePanel) {
        this.binaryTreePanel = binaryTreePanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        LOGGER.debug("mousePressed - Last x: {} Last y: {}", lastX, lastY);
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

        LOGGER.debug("mouseWheelMoved - Grid delta: {}", binaryTreePanel.getDelta());
        binaryTreePanel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int offsetX = binaryTreePanel.getOffsetX() + e.getX() - lastX;
        int offsetY = binaryTreePanel.getOffsetY() - e.getY() + lastY;
        binaryTreePanel.setOffsetX(offsetX);
        binaryTreePanel.setOffsetY(offsetY);
        lastX = e.getX();
        lastY = e.getY();

        LOGGER.debug("mouseDragged - Offset x: {} Offset y: {}", offsetX, offsetY);
        binaryTreePanel.repaint();
    }
}
