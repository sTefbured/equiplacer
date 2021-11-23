package com.kotikov.equiplacer.jbinarytreepanel;

import com.kotikov.equiplacer.binarytree.BinaryTree;
import com.kotikov.equiplacer.jbinarytreepanel.childpanel.CoordinateSystemPanel;
import com.kotikov.equiplacer.jbinarytreepanel.childpanel.TreePanel;
import com.kotikov.equiplacer.jbinarytreepanel.listener.JBinaryTreePropertyChangeListener;
import com.kotikov.equiplacer.jbinarytreepanel.listener.JBinaryTreePropertyChangeListener.DeltaChangeProps;
import com.kotikov.equiplacer.jbinarytreepanel.listener.JBinaryTreePropertyChangeListener.OffsetChangeProps;
import com.kotikov.equiplacer.jbinarytreepanel.listener.JBinaryTreePropertyChangeListener.TreeChangeProps;
import com.kotikov.equiplacer.jbinarytreepanel.listener.impl.CoordinateSystemPanelBinaryTreePropertyChangeListener;
import com.kotikov.equiplacer.jbinarytreepanel.listener.impl.JBinaryTreePanelComponentListener;
import com.kotikov.equiplacer.jbinarytreepanel.listener.impl.JBinaryTreePanelMouseListener;
import com.kotikov.equiplacer.jbinarytreepanel.listener.impl.TreePanelBinaryTreePropertyChangeListener;
import com.kotikov.equiplacer.jbinarytreepanel.node.Node;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class JBinaryTreePanel<T extends Number> extends JLayeredPane {
    private BinaryTree<Node<T>> tree;
    private int delta = 30;
    private int offsetX = 0;
    private int offsetY = 0;
    private final List<JBinaryTreePropertyChangeListener<T>> propertyChangeListeners;

    public JBinaryTreePanel(String xAxisTitle, String yAxisTitle) {
        super();
        CoordinateSystemPanel coordinateSystemPanel = new CoordinateSystemPanel(xAxisTitle, yAxisTitle, delta);
        add(coordinateSystemPanel, JLayeredPane.DEFAULT_LAYER);
        propertyChangeListeners = new LinkedList<>();
        propertyChangeListeners.add(new CoordinateSystemPanelBinaryTreePropertyChangeListener<>(coordinateSystemPanel));

        JBinaryTreePanelMouseListener mouseListener = new JBinaryTreePanelMouseListener();
        addMouseWheelListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addMouseListener(mouseListener);
        addComponentListener(new JBinaryTreePanelComponentListener(this));
    }

    public JBinaryTreePanel(BinaryTree<Node<T>> tree, String xAxisTitle, String yAxisTitle) {
        this(xAxisTitle, yAxisTitle);
        this.tree = tree;
        TreePanel<T> treePanel = new TreePanel<>(tree, delta);
        add(treePanel, new Integer(1));
        propertyChangeListeners.add(new TreePanelBinaryTreePropertyChangeListener<>(treePanel));
    }

    public BinaryTree<Node<T>> getTree() {
        return tree;
    }

    public void setTree(BinaryTree<Node<T>> tree) {
        TreeChangeProps<T> treeChangeProps = new TreeChangeProps<>(this.tree, tree);
        this.tree = tree;
        propertyChangeListeners.forEach(l -> l.treeChanged(treeChangeProps));
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        DeltaChangeProps deltaChangeProps = new DeltaChangeProps(this.delta, delta);
        this.delta = delta;
        propertyChangeListeners.forEach(l -> l.deltaChanged(deltaChangeProps));
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        setOffset(offsetX, this.offsetY);
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        setOffset(this.offsetX, offsetY);
    }

    public void setOffset(int offsetX, int offsetY) {
        OffsetChangeProps offsetChangeProps = new OffsetChangeProps(offsetX, offsetY, this.offsetX, this.offsetY);
        propertyChangeListeners.forEach(l -> l.offsetChanged(offsetChangeProps));
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
}
