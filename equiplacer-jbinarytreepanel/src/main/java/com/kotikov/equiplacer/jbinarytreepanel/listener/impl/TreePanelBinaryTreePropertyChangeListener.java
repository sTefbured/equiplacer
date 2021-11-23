package com.kotikov.equiplacer.jbinarytreepanel.listener.impl;

import com.kotikov.equiplacer.jbinarytreepanel.childpanel.TreePanel;
import com.kotikov.equiplacer.jbinarytreepanel.listener.JBinaryTreePropertyChangeListener;

public class TreePanelBinaryTreePropertyChangeListener<T extends Number> implements JBinaryTreePropertyChangeListener<T> {
    private final TreePanel<T> treePanel;

    public TreePanelBinaryTreePropertyChangeListener(TreePanel<T> treePanel) {
        this.treePanel = treePanel;
    }

    @Override
    public void treeChanged(TreeChangeProps<T> treeChangeProps) {
        treePanel.setTree(treeChangeProps.getNewTree()); //FIXME: not set, but add
        treePanel.revalidate();
    }

    @Override
    public void deltaChanged(DeltaChangeProps deltaChangeProps) {
        treePanel.setDelta(deltaChangeProps.getNewDelta());
        treePanel.revalidate();
    }

    @Override
    public void offsetChanged(OffsetChangeProps offsetChangeProps) {
        treePanel.setOffsetX(offsetChangeProps.getNewOffsetX());
        treePanel.setOffsetY(offsetChangeProps.getNewOffsetY());
        treePanel.revalidate();
    }
}
