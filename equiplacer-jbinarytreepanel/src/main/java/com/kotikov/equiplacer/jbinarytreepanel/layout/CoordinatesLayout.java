package com.kotikov.equiplacer.jbinarytreepanel.layout;

import com.kotikov.equiplacer.binarytree.BinaryTree;
import com.kotikov.equiplacer.jbinarytreepanel.childpanel.CoordinateSystemPanel;
import com.kotikov.equiplacer.jbinarytreepanel.node.Node;

import java.awt.*;

import static com.kotikov.equiplacer.jbinarytreepanel.util.JBinaryTreePanelConstants.GRID_PADDING;

//TODO: replace hardcoded values with constants or variables
public class CoordinatesLayout implements LayoutManager {
    public CoordinatesLayout() {

    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        if (!(parent instanceof CoordinateSystemPanel<? extends Number> panel)) {
            throw new IllegalArgumentException(CoordinateSystemPanel.class.getName() + " container expected but "
                    + parent.getClass().getName() + " provided.");
        }
        int deltaX = panel.getDeltaX();
        int deltaY = panel.getDeltaY();
        int width = 0;
        int height;
        Number maxValue = null;
        Number minValue = null;

        for (BinaryTree<? extends Node<? extends Number>>.Node binaryTreeNode : panel.getTree()) {
            width += deltaX;
            if (maxValue == null || maxValue.doubleValue() < binaryTreeNode.getData().getValue().doubleValue()) {
                maxValue = binaryTreeNode.getData().getValue();
            }
            if (minValue == null || minValue.doubleValue() > binaryTreeNode.getData().getValue().doubleValue()) {
                minValue = binaryTreeNode.getData().getValue();
            }
        }
        if (maxValue == null || minValue == null) {
            throw new RuntimeException("Tree is empty.");
        }
        height = ((int) (maxValue.doubleValue() - minValue.doubleValue()) + 2) * deltaY;
        height += 160;
        width += 160;

        return new Dimension(width, height);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        if (!(parent instanceof CoordinateSystemPanel<? extends Number> coordinateSystemPanel)) {
            throw new IllegalArgumentException(CoordinateSystemPanel.class.getName() + " container expected but "
                    + parent.getClass().getName() + " provided.");
        }
        int deltaX = coordinateSystemPanel.getDeltaX();
        int deltaY = coordinateSystemPanel.getDeltaY();
        for (BinaryTree<? extends Node<? extends Number>>.Node binaryTreeNode : coordinateSystemPanel.getTree()) {
            Node<? extends Number> node = binaryTreeNode.getData();
            int centerX = binaryTreeNode.getDepth() * deltaX + coordinateSystemPanel.getOffsetX() + GRID_PADDING;
            int centerY = (int) (coordinateSystemPanel.getHeight() - node.getValue().doubleValue() * deltaY)
                     - coordinateSystemPanel.getOffsetY() - GRID_PADDING;
            int x = centerX - node.getWidth() / 2;
            int y = centerY - node.getHeight() / 2;
            node.setBounds(x, y, node.getWidth(), node.getHeight());
            node.getBounds();
        }
    }
}