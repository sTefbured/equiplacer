package com.kotikov.equiplacer.jbinarytreepanel.childpanel;

import com.kotikov.equiplacer.binarytree.BinaryTree;
import com.kotikov.equiplacer.jbinarytreepanel.layout.CoordinatesLayout;
import com.kotikov.equiplacer.jbinarytreepanel.node.Node;

import javax.swing.*;
import java.awt.*;

import static com.kotikov.equiplacer.jbinarytreepanel.util.JBinaryTreePanelConstants.GRID_PADDING;

public class CoordinateSystemPanel<T extends Number> extends JPanel {
    private static final int AXIS_LINE_WIDTH = 6;
    private static final Color AXIS_LINE_COLOR = Color.BLACK;
    private static final Stroke AXIS_LINE_STROKE = new BasicStroke(AXIS_LINE_WIDTH);
    private static final int VALUE_LINE_WIDTH = 3;
    private static final int GRID_LINE_WIDTH = 1;
    private static final Stroke VALUE_LINE_STROKE = new BasicStroke(VALUE_LINE_WIDTH);
    private static final Stroke GRID_LINE_STROKE = new BasicStroke(GRID_LINE_WIDTH);
    private static final Color VALUE_LINE_COLOR = Color.BLACK;
    private static final Color GRID_LINE_COLOR = Color.BLUE;
    private static final int MIN_DELTA_X = 30;
    private static final int MIN_DELTA_Y = 20;
    private static final Color CONNECTION_COLOR = Color.BLACK;

    private BinaryTree<Node<T>> tree;
    private int offsetX;
    private int offsetY;
    private int deltaX;
    private int deltaY;

    public CoordinateSystemPanel(BinaryTree<Node<T>> tree) {
        super();
        setBackground(Color.WHITE);
        setLayout(new CoordinatesLayout());
        this.tree = tree;
        this.deltaX = 30;
        this.deltaY = 20;
        for (BinaryTree<Node<T>>.Node binaryTreeNode : tree) {
            add(binaryTreeNode.getData());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawGrid(graphics2D);
        drawAxes(graphics2D);
        drawConnectionsBetweenNodes(graphics2D);
    }

    private void drawConnectionsBetweenNodes(Graphics2D graphics2D) {
        var oldColor = graphics2D.getColor();
        graphics2D.setColor(CONNECTION_COLOR);
        tree.forEach(node -> {
            var parentBounds = node.getData().getBounds();
            var leftChild = node.getLeftChild();
            var rightChild = node.getRightChild();
            if (leftChild != null) {
                var leftChildBounds = leftChild.getData().getBounds();
                drawConnection(graphics2D, (int)parentBounds.getCenterX(), (int)parentBounds.getCenterY(),
                        (int)leftChildBounds.getCenterX(), (int)leftChildBounds.getCenterY());
            }
            if (rightChild != null) {
                var rightChildBounds = rightChild.getData().getBounds();
                drawConnection(graphics2D, (int)parentBounds.getCenterX(), (int)parentBounds.getCenterY(),
                        (int)rightChildBounds.getCenterX(), (int)rightChildBounds.getCenterY());
            }
        });
        graphics2D.setColor(oldColor);
    }

    private void drawConnection(Graphics2D graphics2D, int x1, int y1, int x2, int y2) {
        graphics2D.drawLine(x1, y1, x2, y2);
    }

    private void drawAxes(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        Color oldColor = graphics2D.getColor();
        graphics2D.setStroke(AXIS_LINE_STROKE);
        graphics2D.setColor(AXIS_LINE_COLOR);

        graphics2D.drawLine(GRID_PADDING, 0, GRID_PADDING, getHeight() - GRID_PADDING);
        graphics2D.drawLine(GRID_PADDING, getHeight() - GRID_PADDING, getWidth(), getHeight() - GRID_PADDING);

        graphics2D.setStroke(oldStroke);
        graphics2D.setColor(oldColor);
    }

    private void drawGrid(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        Color oldColor = graphics2D.getColor();

        int firstValueX = -offsetX / deltaX;
        int firstValueY = -offsetY / deltaY;
        if (offsetX < 0) {
            firstValueX++;
        }
        if (offsetY < 0) {
            firstValueY++;
        }
        int firstX = offsetX + firstValueX * deltaX + GRID_PADDING;
        int firstY = -offsetY - firstValueY * deltaY + getHeight() - GRID_PADDING;
        for (int x = firstX, value = firstValueX; x < getWidth(); x += deltaX, value++) {
            drawVerticalGridLine(graphics2D, x, value);
        }
        for (int y = firstY, value = firstValueY; y > 0; y -= deltaY, value++) {
            drawHorizontalGridLine(graphics2D, y, value);
        }

        graphics2D.setStroke(oldStroke);
        graphics2D.setColor(oldColor);
    }

    private void drawVerticalGridLine(Graphics2D graphics2D, int x, int value) {
        graphics2D.setStroke(GRID_LINE_STROKE);
        graphics2D.setColor(GRID_LINE_COLOR);
        graphics2D.drawLine(x, 0, x, getHeight() - GRID_PADDING);
        graphics2D.setStroke(VALUE_LINE_STROKE);
        graphics2D.setColor(VALUE_LINE_COLOR);
        graphics2D.drawLine(x, getHeight() + 5 - GRID_PADDING, x, getHeight() - 5 - GRID_PADDING);

        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        String valueStr = String.valueOf(value);
        graphics2D.drawString(valueStr, x - fontMetrics.stringWidth(valueStr) / 2, getHeight() + 30 - GRID_PADDING);
    }

    private void drawHorizontalGridLine(Graphics2D graphics2D, int y, int value) {
        graphics2D.setStroke(GRID_LINE_STROKE);
        graphics2D.setColor(GRID_LINE_COLOR);
        graphics2D.drawLine(GRID_PADDING, y, getWidth(), y);
        graphics2D.setStroke(VALUE_LINE_STROKE);
        graphics2D.setColor(VALUE_LINE_COLOR);
        graphics2D.drawLine(GRID_PADDING - 5, y, GRID_PADDING + 5, y);

        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        String valueStr = String.valueOf(value);
        graphics2D.drawString(valueStr, GRID_PADDING - 25 - fontMetrics.stringWidth(valueStr) / 2,
                y + fontMetrics.getHeight() / 2 - 3);
    }

    public BinaryTree<Node<T>> getTree() {
        return tree;
    }

    public void setTree(BinaryTree<Node<T>> tree) {
        this.tree = tree;
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

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = Math.max(deltaX, MIN_DELTA_X);
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = Math.max(deltaY, MIN_DELTA_Y);
    }
}
