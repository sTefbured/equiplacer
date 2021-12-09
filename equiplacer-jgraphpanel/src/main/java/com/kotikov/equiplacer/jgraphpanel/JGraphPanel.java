package com.kotikov.equiplacer.jgraphpanel;

import com.kotikov.equiplacer.graph.Graph;
import com.kotikov.equiplacer.jgraphpanel.childpanel.CoordinateSystemPanel;
import com.kotikov.equiplacer.jgraphpanel.listener.impl.JGraphPanelComponentListener;
import com.kotikov.equiplacer.jgraphpanel.listener.impl.JGraphPanelMouseListener;
import com.kotikov.equiplacer.jgraphpanel.node.NodeComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static com.kotikov.equiplacer.jgraphpanel.util.JGraphPanelConstants.COORDINATE_SYSTEM_PADDING;

public class JGraphPanel<T extends Number> extends JPanel {
    private static final double Y_AXIS_TITLE_ROTATION_ANGLE = -Math.PI / 2;

    private final String xAxisTitle;
    private final String yAxisTitle;
    private final Font axisTitleFont;

    private int offsetX;
    private int offsetY;

    private CoordinateSystemPanel<T> coordinateSystemPanel;

    public JGraphPanel(Graph<NodeComponent<T>> graph, String xAxisTitle, String yAxisTitle) {
        super();
        setLayout(null);
        this.xAxisTitle = xAxisTitle;
        this.yAxisTitle = yAxisTitle;
        setBackground(Color.WHITE);
        addComponentListener(new JGraphPanelComponentListener(this));
        var mouseListener = new JGraphPanelMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addMouseWheelListener(mouseListener);
        coordinateSystemPanel = new CoordinateSystemPanel<>(graph);
        coordinateSystemPanel.setBounds(COORDINATE_SYSTEM_PADDING, COORDINATE_SYSTEM_PADDING,
                getWidth() - 2 * COORDINATE_SYSTEM_PADDING, getHeight() - 2 * COORDINATE_SYSTEM_PADDING);
        add(coordinateSystemPanel);
        axisTitleFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawAxisTitles(graphics2D);
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
        graphics2D.drawString(yAxisTitle, -yAxisTitleWidth / 2, yAxisTitleHeight / 2);
        graphics2D.setTransform(oldTransform);
        graphics2D.setFont(oldFont);
    }

    public CoordinateSystemPanel<T> getCoordinateSystemPanel() {
        return coordinateSystemPanel;
    }

    public void setCoordinateSystemPanel(CoordinateSystemPanel<T> coordinateSystemPanel) {
        this.coordinateSystemPanel = coordinateSystemPanel;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
        coordinateSystemPanel.setOffsetX(offsetX);
        coordinateSystemPanel.revalidate();
        coordinateSystemPanel.repaint();
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
        coordinateSystemPanel.setOffsetY(offsetY);
        coordinateSystemPanel.revalidate();
        coordinateSystemPanel.repaint();
    }

    public void incrementDelta(int incrementValue) {
        coordinateSystemPanel.setDeltaX(coordinateSystemPanel.getDeltaX() + incrementValue);
        coordinateSystemPanel.setDeltaY(coordinateSystemPanel.getDeltaY() + incrementValue);
        coordinateSystemPanel.revalidate();
        coordinateSystemPanel.repaint();
    }
}
