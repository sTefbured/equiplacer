package com.kotikov.equiplacer.jbinarytreepanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class JBinaryTreePanel extends JPanel {
    private static final Logger LOGGER = LogManager.getLogger(JBinaryTreePanel.class);

    private int delta = 30;

    public JBinaryTreePanel() {
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() > 0) {
                    delta += 1;
                } else {
                    delta -= 1;
                }

                if (delta < 15) {
                    delta = 15;
                }
                LOGGER.debug(delta);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawLine(5, 5, 5, getHeight() - 5);
        graphics2D.drawLine(5, getHeight() - 5, getWidth() - 5, getHeight() - 5);

        graphics2D.setStroke(new BasicStroke(1));
        int x = delta;
        while (x < getWidth()) {
            graphics2D.drawLine(5 + x, 5, 5 + x, getHeight() - 5);
            x += delta;
        }
        int y = getHeight() - delta;
        while (y > 0) {
            graphics2D.drawLine(5, y, getWidth() - 5, y);
            y -= delta - 10;
        }
    }
}
