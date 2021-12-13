package com.kotikov.equiplacer.graph;

import com.kotikov.equiplacer.graph.iterator.DefaultGraphIterator;
import com.kotikov.equiplacer.graph.iterator.LayeredGraphIterator;

import java.util.*;
import java.util.function.Function;

public class Graph<T> implements Iterable<Node<T>> {
    private NodeContainer<T> nodeContainer;

    private Graph() {
        nodeContainer = new NodeContainer<>();
    }

    public Graph(int[][] adjacencyMatrix, List<T> data) {
        this();
        initialize(adjacencyMatrix, data);
    }

    private void initialize(int[][] adjacencyMatrix, List<T> data) {
        if (adjacencyMatrix.length != adjacencyMatrix[0].length || adjacencyMatrix.length != data.size()) {
            throw new IllegalArgumentException("The sizes of matrix and data list expected to be equal.");
        }
        nodeContainer.addRange(data, adjacencyMatrix);
    }

    public Iterator<List<Node<T>>> layeredIterator() {
        return new LayeredGraphIterator<>(nodeContainer.getNodeById(1));
    }

    public <E> Graph<E> map(Function<T, E> mapper) {
        var result = new Graph<E>();
        result.nodeContainer = nodeContainer.map(mapper);
        return result;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new DefaultGraphIterator<>(this);
    }

    public NodeContainer<T> getNodeContainer() {
        return nodeContainer;
    }
}
