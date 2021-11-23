package com.kotikov.equiplacer.jbinarytreepanel.childpanel;

import com.kotikov.equiplacer.binarytree.BinaryTree;
import com.kotikov.equiplacer.jbinarytreepanel.layout.CoordinatesLayout;
import com.kotikov.equiplacer.jbinarytreepanel.node.Node;

import javax.swing.*;

public class TreePanel<T extends Number> extends JPanel implements JBinaryTreeChildPanel {
    private BinaryTree<Node<T>> tree;
    private int offsetX;
    private int offsetY;
    private int delta;

    public TreePanel(BinaryTree<Node<T>> tree, int delta) {
        super();
        setOpaque(false);
        setLayout(new CoordinatesLayout());
        this.tree = tree;
        this.delta = delta;
        for (BinaryTree<Node<T>>.Node binaryTreeNode : tree) {
            add(binaryTreeNode.getData());
        }
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

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = Math.max(delta, 20);
    }
}
