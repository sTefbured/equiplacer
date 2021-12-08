package com.kotikov.equiplacer.core.service;

import com.kotikov.equiplacer.graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentGraphServiceTest {
    private int[][] adjacencyMatrix;
    private List<Integer> data;
    private List<Integer> startAges;

    @BeforeEach
    public void initialize() {
        adjacencyMatrix = new int[][] {
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        data = List.of(3, 4, 5, 3, 4, 2, 1, 2, 3, 2, 3, 4, 5);
        startAges = List.of(3, 2, 1, 3, 2);
    }

    @Test
    public void getEquipmentGraph() {
        var expectation = new Graph<>(adjacencyMatrix, data);
        var service = new EquipmentGraphService();
        var actuality = service.getEquipmentGraph(5, 5, startAges);
        var expectationIterator = expectation.iterator();
        for (var node : actuality) {
            var expectationNode = expectationIterator.next();
            assertEquals(expectationNode.getData(), node.getData());
            var expConnectionsIterator = expectationNode.getConnections().iterator();
            for (var connection : node.getConnections()) {
                assertEquals(expConnectionsIterator.next().getData(), connection.getData());
            }
        }
    }
}