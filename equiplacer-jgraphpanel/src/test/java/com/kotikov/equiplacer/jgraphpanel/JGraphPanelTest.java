package com.kotikov.equiplacer.jgraphpanel;

import com.kotikov.equiplacer.graph.Graph;
import com.kotikov.equiplacer.jgraphpanel.node.NodeComponent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class JGraphPanelTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new GridLayout(1, 1));

            var adjacencyMatrix = new int[][]{
                    {0, 1, 1, 0},
                    {1, 0, 0, 1},
                    {1, 0, 0, 0},
                    {0, 1, 0, 0}
            };
            var data = List.of(new NodeComponent<>(3, 30),
                    new NodeComponent<>(4, 30), new NodeComponent<>(1, 30), new NodeComponent<>(1, 30));
            Graph<NodeComponent<Integer>> graph = new Graph<>(adjacencyMatrix, data);

            JGraphPanel<Integer> graphPanel = new JGraphPanel<>(graph, "Year", "Age");
            frame.add(graphPanel);
            frame.setVisible(true);
        });
    }
}