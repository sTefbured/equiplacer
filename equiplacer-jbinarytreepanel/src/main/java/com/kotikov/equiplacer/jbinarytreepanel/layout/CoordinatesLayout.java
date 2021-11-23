package com.kotikov.equiplacer.jbinarytreepanel.layout;

import com.kotikov.equiplacer.binarytree.BinaryTree;
import com.kotikov.equiplacer.jbinarytreepanel.childpanel.TreePanel;
import com.kotikov.equiplacer.jbinarytreepanel.node.Node;

import java.awt.*;

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
        if (!(parent instanceof TreePanel)) {
            throw new IllegalArgumentException("TreePanel container expected but "
                    + parent.getClass().getName() + " provided.");
        }
        TreePanel<? extends Number> treePanel = (TreePanel<? extends Number>) parent;
        int delta = treePanel.getDelta();
        int width = 0;
        int height;
        Number maxValue = null;
        Number minValue = null;

        for (BinaryTree<? extends Node<? extends Number>>.Node binaryTreeNode : treePanel.getTree()) {
            width += delta;
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
        height = ((int) (maxValue.doubleValue() - minValue.doubleValue()) + 2) * delta;
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
        if (!(parent instanceof TreePanel)) {
            throw new IllegalArgumentException("TreePanel container expected but "
                    + parent.getClass().getName() + " provided.");
        }
        TreePanel<? extends Number> treePanel = (TreePanel<? extends Number>) parent;
        int delta = treePanel.getDelta();
        for (BinaryTree<? extends Node<? extends Number>>.Node binaryTreeNode : treePanel.getTree()) {
            Node<? extends Number> node = binaryTreeNode.getData();
            int centerX = binaryTreeNode.getDepth() * delta + 80 + treePanel.getOffsetX(); // 80 - GRID_PADDING
            int centerY = (int) (treePanel.getHeight() - node.getValue().doubleValue() * (delta - 10))
                    - 80 - treePanel.getOffsetY();
            int x = centerX - node.getWidth() / 2;
            int y = centerY - node.getHeight() / 2;
            node.setBounds(x, y, node.getWidth(), node.getHeight());
            node.getBounds();
        }
    }
}