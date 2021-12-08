package com.kotikov.equiplacer.jgraphpanel.layout;

import com.kotikov.equiplacer.graph.Node;
import com.kotikov.equiplacer.jgraphpanel.childpanel.CoordinateSystemPanel;
import com.kotikov.equiplacer.jgraphpanel.node.NodeComponent;

import java.awt.*;

import static com.kotikov.equiplacer.jgraphpanel.util.JGraphPanelConstants.GRID_PADDING;

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

        for (Node<? extends NodeComponent<? extends Number>> graphNode : panel.getGraph()) {
            width += deltaX;
            if (maxValue == null || maxValue.doubleValue() < graphNode.getData().getValue().doubleValue()) {
                maxValue = graphNode.getData().getValue();
            }
            if (minValue == null || minValue.doubleValue() > graphNode.getData().getValue().doubleValue()) {
                minValue = graphNode.getData().getValue();
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
        int internalOffsetX = 0;
        var layeredIterator = coordinateSystemPanel.getGraph().layeredIterator();
        while (layeredIterator.hasNext()) {
            var layer = layeredIterator.next();
            int finalInternalOffsetX = internalOffsetX;
            layer.forEach(entry -> {
                int centerX = finalInternalOffsetX + coordinateSystemPanel.getOffsetX() + GRID_PADDING;
                int centerY = (int) (coordinateSystemPanel.getHeight() - entry.getData().getValue().doubleValue() * deltaY)
                        - coordinateSystemPanel.getOffsetY() - GRID_PADDING;
                int x = centerX - entry.getData().getWidth() / 2;
                int y = centerY - entry.getData().getHeight() / 2;
                entry.getData().setBounds(x, y, entry.getData().getWidth(), entry.getData().getHeight());
                entry.getData().getBounds();
            });
            internalOffsetX += deltaX;
        }
    }
}