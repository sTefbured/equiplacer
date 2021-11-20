package com.kotikov.equiplacer.jbinarytreepanel;

import com.kotikov.equiplacer.binarytree.BinaryTree;
import com.kotikov.equiplacer.jbinarytreepanel.childpanel.CoordinateSystemPanel;
import com.kotikov.equiplacer.jbinarytreepanel.listener.JBinaryTreePanelComponentListener;
import com.kotikov.equiplacer.jbinarytreepanel.node.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class JBinaryTreePanel<T extends Number> extends JLayeredPane {
    private static final Logger LOGGER = LogManager.getLogger(JBinaryTreePanel.class);

    private final CoordinateSystemPanel coordinateSystemPanel;
    private BinaryTree<Node<T>> tree;

    public JBinaryTreePanel(String xAxisTitle, String yAxisTitle) {
        coordinateSystemPanel = new CoordinateSystemPanel(xAxisTitle, yAxisTitle);
        add(coordinateSystemPanel, JLayeredPane.DEFAULT_LAYER);
        addComponentListener(new JBinaryTreePanelComponentListener(this));
    }

    public JBinaryTreePanel(BinaryTree<Node<T>> tree, String xAxisTitle, String yAxisTitle) {
        this(xAxisTitle, yAxisTitle);
        this.tree = tree;
    }

    public void setTree(BinaryTree<Node<T>> tree) {
        this.tree = tree;
        repaint();
    }
}
