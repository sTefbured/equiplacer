package com.kotikov.equiplacer.graph;

import java.util.*;

public class Graph<T> implements Iterable<Node<T>> {
    private final NodeContainer<T> nodeContainer;

    public Graph(int[][] adjacencyMatrix, List<T> data) {
        nodeContainer = new NodeContainer<>();
        initialize(adjacencyMatrix, data);
    }

    public void initialize(int[][] adjacencyMatrix, List<T> data) {
        if (adjacencyMatrix.length != adjacencyMatrix[0].length || adjacencyMatrix.length != data.size()) {
            throw new IllegalArgumentException("The sizes of matrix and data list expected to be equal.");
        }
        nodeContainer.addRange(data, adjacencyMatrix);
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new GraphIterator<>(this);
    }

    NodeContainer<T> getNodeContainer() {
        return nodeContainer;
    }
}
