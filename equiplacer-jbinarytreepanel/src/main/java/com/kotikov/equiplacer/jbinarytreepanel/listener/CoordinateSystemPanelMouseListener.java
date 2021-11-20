package com.kotikov.equiplacer.jbinarytreepanel.listener;

import com.kotikov.equiplacer.jbinarytreepanel.childpanel.CoordinateSystemPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class CoordinateSystemPanelMouseListener extends MouseAdapter {
    private static final Logger LOGGER = LogManager.getLogger(CoordinateSystemPanelMouseListener.class);
    private static final int DELTA_STEP = 10;

    private final CoordinateSystemPanel coordinateSystemPanel;

    private int lastX;
    private int lastY;

    public CoordinateSystemPanelMouseListener(CoordinateSystemPanel coordinateSystemPanel) {
        this.coordinateSystemPanel = coordinateSystemPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        LOGGER.debug("mousePressed - Last x: {} Last y: {}", lastX, lastY);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int newDelta = coordinateSystemPanel.getDelta();
        if (e.getWheelRotation() > 0) {
            newDelta += DELTA_STEP;
        } else {
            newDelta -= DELTA_STEP;
        }
        coordinateSystemPanel.setDelta(newDelta);

        LOGGER.debug("mouseWheelMoved - Grid delta: {}", coordinateSystemPanel.getDelta());
        coordinateSystemPanel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int offsetX = coordinateSystemPanel.getOffsetX() + e.getX() - lastX;
        int offsetY = coordinateSystemPanel.getOffsetY() - e.getY() + lastY;
        coordinateSystemPanel.setOffsetX(offsetX);
        coordinateSystemPanel.setOffsetY(offsetY);
        lastX = e.getX();
        lastY = e.getY();

        LOGGER.debug("mouseDragged - Offset x: {} Offset y: {}", offsetX, offsetY);
        coordinateSystemPanel.repaint();
    }
}
