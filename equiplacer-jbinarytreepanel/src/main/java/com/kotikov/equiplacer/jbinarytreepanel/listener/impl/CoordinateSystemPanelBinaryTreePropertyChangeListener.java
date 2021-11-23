package com.kotikov.equiplacer.jbinarytreepanel.listener.impl;

import com.kotikov.equiplacer.jbinarytreepanel.childpanel.CoordinateSystemPanel;
import com.kotikov.equiplacer.jbinarytreepanel.listener.JBinaryTreePropertyChangeListener;

public class CoordinateSystemPanelBinaryTreePropertyChangeListener<T extends Number> implements JBinaryTreePropertyChangeListener<T> {
    private final CoordinateSystemPanel coordinateSystemPanel;

    public CoordinateSystemPanelBinaryTreePropertyChangeListener(CoordinateSystemPanel coordinateSystemPanel) {
        this.coordinateSystemPanel = coordinateSystemPanel;
    }

    @Override
    public void treeChanged(TreeChangeProps<T> treeChangeProps) {
    }

    @Override
    public void deltaChanged(DeltaChangeProps deltaChangeProps) {
        coordinateSystemPanel.setDelta(deltaChangeProps.getNewDelta());
        coordinateSystemPanel.repaint();
    }

    @Override
    public void offsetChanged(OffsetChangeProps offsetChangeProps) {
        coordinateSystemPanel.setOffsetX(offsetChangeProps.getNewOffsetX());
        coordinateSystemPanel.setOffsetY(offsetChangeProps.getNewOffsetY());
        coordinateSystemPanel.repaint();
    }
}
