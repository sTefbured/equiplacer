package com.kotikov.equiplacer.jbinarytreepanel.childpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class CoordinateSystemPanel extends JPanel implements JBinaryTreeChildPanel {
    private static final double Y_AXIS_TITLE_ROTATION_ANGLE = -Math.PI / 2;
    private static final int AXIS_LINE_WIDTH = 6;
    private static final Color AXIS_LINE_COLOR = Color.BLACK;
    private static final Stroke AXIS_LINE_STROKE = new BasicStroke(AXIS_LINE_WIDTH);
    private static final int VALUE_LINE_WIDTH = 3;
    private static final int GRID_LINE_WIDTH = 1;
    private static final Stroke VALUE_LINE_STROKE = new BasicStroke(VALUE_LINE_WIDTH);
    private static final Stroke GRID_LINE_STROKE = new BasicStroke(GRID_LINE_WIDTH);
    private static final int GRID_PADDING = 80;
    private static final Color VALUE_LINE_COLOR = Color.BLACK;
    private static final Color GRID_LINE_COLOR = Color.BLUE;

    private final String xAxisTitle;
    private final String yAxisTitle;
    private final Font axisTitleFont;

    private int offsetX;
    private int offsetY;
    private int delta;

    public CoordinateSystemPanel(String xAxisTitle, String yAxisTitle, int delta) {
        super();
        this.xAxisTitle = xAxisTitle;
        this.yAxisTitle = yAxisTitle;
        this.delta = delta;
        setOpaque(false);
        axisTitleFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawGrid(graphics2D);
        drawAxes(graphics2D);
    }

    private void drawAxes(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        Color oldColor = graphics2D.getColor();
        graphics2D.setStroke(AXIS_LINE_STROKE);
        graphics2D.setColor(AXIS_LINE_COLOR);

        graphics2D.drawLine(GRID_PADDING, GRID_PADDING, GRID_PADDING, getHeight() - GRID_PADDING);
        graphics2D.drawLine(GRID_PADDING, getHeight() - GRID_PADDING,
                getWidth() - GRID_PADDING, getHeight() - GRID_PADDING);
        drawAxisTitles(graphics2D);

        graphics2D.setStroke(oldStroke);
        graphics2D.setColor(oldColor);
    }

    private void drawAxisTitles(Graphics2D graphics2D) {
        Font oldFont = graphics2D.getFont();
        graphics2D.setFont(axisTitleFont);

        FontMetrics axisTitleFontMetrics = graphics2D.getFontMetrics();
        int xAxisTitleWidth = axisTitleFontMetrics.stringWidth(xAxisTitle);
        int yAxisTitleWidth = axisTitleFontMetrics.stringWidth(yAxisTitle);
        int yAxisTitleHeight = axisTitleFontMetrics.getHeight();

        graphics2D.drawString(xAxisTitle, getWidth() / 2 - xAxisTitleWidth / 2, getHeight() - 20);
        AffineTransform oldTransform = graphics2D.getTransform();
        graphics2D.translate(20, getHeight() / 2.0);
        graphics2D.rotate(Y_AXIS_TITLE_ROTATION_ANGLE, 0, 0);
        graphics2D.drawString(yAxisTitle, -yAxisTitleWidth / 2,  yAxisTitleHeight / 2);
        graphics2D.setTransform(oldTransform);
        graphics2D.setFont(oldFont);
    }

    private void drawGrid(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        Color oldColor = graphics2D.getColor();

        int firstValueX = -offsetX / delta;
        int firstValueY = -offsetY / (delta - 10);
        if (offsetX < 0) {
            firstValueX++;
        }
        if (offsetY < 0) {
            firstValueY++;
        }
        int firstX = offsetX + firstValueX * delta;
        int firstY = -offsetY - firstValueY * (delta - 10) + getHeight() - 2 * GRID_PADDING;
        for (int x = firstX, value = firstValueX; x < getWidth() - 2 * GRID_PADDING; x += delta, value++) {
            drawVerticalGridLines(graphics2D, x, value);
        }
        for (int y = firstY, value = firstValueY; y > 0; y -= delta - 10, value++) {
            drawHorizontalGridLines(graphics2D, y, value);
        }

        graphics2D.setStroke(oldStroke);
        graphics2D.setColor(oldColor);
    }

    private void drawVerticalGridLines(Graphics2D graphics2D, int x, int value) {
        graphics2D.setStroke(GRID_LINE_STROKE);
        graphics2D.setColor(GRID_LINE_COLOR);
        graphics2D.drawLine(x + GRID_PADDING, GRID_PADDING, x + GRID_PADDING, getHeight() - GRID_PADDING);
        graphics2D.setStroke(VALUE_LINE_STROKE);
        graphics2D.setColor(VALUE_LINE_COLOR);
        graphics2D.drawLine(x + GRID_PADDING, getHeight() - GRID_PADDING + 5,
                x + GRID_PADDING, getHeight() - GRID_PADDING - 5);

        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        String valueStr = String.valueOf(value);
        graphics2D.drawString(valueStr, x + GRID_PADDING - fontMetrics.stringWidth(valueStr) / 2,
                getHeight() - GRID_PADDING + 30);
    }

    private void drawHorizontalGridLines(Graphics2D graphics2D, int y, int value) {
        graphics2D.setStroke(GRID_LINE_STROKE);
        graphics2D.setColor(GRID_LINE_COLOR);
        graphics2D.drawLine(GRID_PADDING, y + GRID_PADDING, getWidth() - GRID_PADDING, y + GRID_PADDING);
        graphics2D.setStroke(VALUE_LINE_STROKE);
        graphics2D.setColor(VALUE_LINE_COLOR);
        graphics2D.drawLine(GRID_PADDING - 5, y + GRID_PADDING, GRID_PADDING + 5, y + GRID_PADDING);

        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        String valueStr = String.valueOf(value);
        graphics2D.drawString(valueStr, GRID_PADDING - 25 - fontMetrics.stringWidth(valueStr) / 2,
                y + GRID_PADDING + fontMetrics.getHeight() / 2 - 3);
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = Math.max(delta, 20);
    }
}
