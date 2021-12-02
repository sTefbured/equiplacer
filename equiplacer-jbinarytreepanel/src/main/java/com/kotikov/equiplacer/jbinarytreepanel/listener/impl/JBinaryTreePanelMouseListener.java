package com.kotikov.equiplacer.jbinarytreepanel.listener.impl;

import com.kotikov.equiplacer.jbinarytreepanel.JBinaryTreePanel;

import java.awt.event.*;

public class JBinaryTreePanelMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

    private static final int DELTA_STEP = 10;

    private int lastX;
    private int lastY;

    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (!(e.getSource() instanceof JBinaryTreePanel<? extends Number> source)) {
            throw new IllegalArgumentException("Illegal event source: JBinaryTreePanel object expected, but "
                    + e.getSource().getClass().getName() + " provided.");
        }
        source.incrementDelta(e.getWheelRotation() > 0 ? DELTA_STEP : -DELTA_STEP);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!(e.getSource() instanceof JBinaryTreePanel<? extends Number> source)) {
            throw new IllegalArgumentException("Illegal event source: JBinaryTreePanel object expected, but "
                    + e.getSource().getClass().getName() + " provided.");
        }
        int offsetX = source.getOffsetX() + e.getX() - lastX;
        int offsetY = source.getOffsetY() - e.getY() + lastY;
        source.setOffsetX(offsetX);
        source.setOffsetY(offsetY);
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
