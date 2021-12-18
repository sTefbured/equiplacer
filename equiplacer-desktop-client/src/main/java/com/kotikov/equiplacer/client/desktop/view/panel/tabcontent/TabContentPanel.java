package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent;

import com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input.InputPanel;
import com.kotikov.equiplacer.graph.Graph;
import com.kotikov.equiplacer.jgraphpanel.JGraphPanel;
import com.kotikov.equiplacer.jgraphpanel.node.NodeComponent;

import javax.swing.*;
import java.awt.*;

public class TabContentPanel extends JPanel {
    private static final String X_AXIS_TITLE = "Year";
    private static final String Y_AXIS_TITLE = "Age";

    private JGraphPanel<Integer> graphPanel;

    public TabContentPanel(JTabbedPane parentPane) {
        super(new GridLayout(1, 2));
        add(new InputPanel(this));
        graphPanel = new JGraphPanel<>(X_AXIS_TITLE, Y_AXIS_TITLE);
        add(graphPanel);
    }

    public void initializeGraph(Graph<Integer> graph) {
        var componentsGraph = graph.map(entry -> new NodeComponent<>(entry, 30));
        remove(graphPanel);
        graphPanel = new JGraphPanel<>(componentsGraph, X_AXIS_TITLE, Y_AXIS_TITLE);
        add(graphPanel);
        revalidate();
        repaint();
    }

    public JGraphPanel<Integer> getGraphPanel() {
        return graphPanel;
    }
}
