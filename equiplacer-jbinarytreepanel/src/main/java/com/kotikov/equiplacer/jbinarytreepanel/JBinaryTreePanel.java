package com.kotikov.equiplacer.jbinarytreepanel;

import com.kotikov.equiplacer.jbinarytreepanel.listener.JBinaryTreePanelMouseWheelListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class JBinaryTreePanel extends JPanel {
    private static final Logger LOGGER = LogManager.getLogger(JBinaryTreePanel.class);

    private static final Color GRID_COLOR = Color.BLUE;
    private static final Color AXIS_COLOR = Color.BLACK;

    private static final int GRID_LINE_WIDTH = 1;
    private static final int AXIS_LINE_WIDTH = 6;
    private static final Stroke GRID_LINE_STROKE = new BasicStroke(GRID_LINE_WIDTH);
    private static final Stroke AXIS_LINE_STROKE = new BasicStroke(AXIS_LINE_WIDTH);
    private static final int GRID_PADDING = 50;

    private final String xAxisTitle;
    private final String yAxisTitle;

    private int delta = 30;

    public JBinaryTreePanel(String xAxisTitle, String yAxisTitle) {
        this.xAxisTitle = xAxisTitle;
        this.yAxisTitle = yAxisTitle;
        addMouseWheelListener(new JBinaryTreePanelMouseWheelListener(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawGrid(graphics2D);
        drawAxis(graphics2D);

    }

    private void drawAxis(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        Color oldColor = graphics2D.getColor();
        graphics2D.setStroke(AXIS_LINE_STROKE);
        graphics2D.setColor(AXIS_COLOR);

        graphics2D.drawLine(GRID_PADDING, GRID_PADDING, GRID_PADDING, getHeight() - GRID_PADDING);
        graphics2D.drawLine(GRID_PADDING, getHeight() - GRID_PADDING,
                getWidth() - GRID_PADDING, getHeight() - GRID_PADDING);
        graphics2D.drawString(xAxisTitle, getWidth() / 2, getHeight() - 5);

        graphics2D.setStroke(oldStroke);
        graphics2D.setColor(oldColor);
    }

    private void drawGrid(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        Color oldColor = graphics2D.getColor();
        graphics2D.setStroke(GRID_LINE_STROKE);
        graphics2D.setColor(GRID_COLOR);

        for (int x = delta; x < getWidth() - 2 * GRID_PADDING; x += delta) {
            graphics2D.drawLine(x + GRID_PADDING, GRID_PADDING, x + GRID_PADDING, getHeight() - GRID_PADDING);
        }
        for (int y = getHeight() - delta + 10 - 2 * GRID_PADDING; y > 0; y -= delta - 10) {
            graphics2D.drawLine(GRID_PADDING, y + GRID_PADDING, getWidth() - GRID_PADDING, y + GRID_PADDING);
        }

        graphics2D.setStroke(oldStroke);
        graphics2D.setColor(oldColor);
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = Math.max(delta, 15);
    }
}
