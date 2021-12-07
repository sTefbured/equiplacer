package com.kotikov.equiplacer.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private int nodesCount;
    private List<Integer> data;
    private int[][] adjacencyMatrix;

    private Iterator<Node<Integer>> iterator;

    @BeforeEach
    public void initialize() {
        adjacencyMatrix = new int[][]{
                {0, 1, 0, 1},
                {1, 0, 1, 1},
                {0, 1, 0, 1},
                {1, 1, 1, 0}
        };
        nodesCount = adjacencyMatrix.length;
        data = List.of(11, 22, 33, 44);
        var graph = new Graph<>(adjacencyMatrix, data);
        iterator = graph.iterator();
    }

    @Test
    void iterator_next() {
        assertInstanceOf(GraphIterator.class, iterator, "Incorrect iterator type. Check if the test is out of date");
        for (int i = 0; i < nodesCount; i++) {
            var node = iterator.next();
            assertValidNode(node, i);
        }
    }

    private void assertValidNode(Node<Integer> node, int i) {
        assertEquals(data.get(i), node.getData());
        for (int j = 0; j < nodesCount; j++) {
            if (adjacencyMatrix[i][j] == 0) {
                continue;
            }
            int finalJ = j;
            assertTrue(node.getConnections().stream().anyMatch(n -> n.getData().equals(data.get(finalJ))));
        }
    }

    @Test
    void iterator_hasNext() {
        var testFailMessage = "Iterator.hasNext() returned incorrect value";
        assertInstanceOf(GraphIterator.class, iterator, "Incorrect iterator type. Check if the test is out of date");
        for (int i = 0; i < nodesCount; i++) {
            assertTrue(iterator.hasNext(), testFailMessage);
            iterator.next();
        }
        assertFalse(iterator.hasNext(), testFailMessage);
    }
}