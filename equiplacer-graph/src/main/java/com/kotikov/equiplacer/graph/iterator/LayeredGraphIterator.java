package com.kotikov.equiplacer.graph.iterator;

import com.kotikov.equiplacer.graph.Node;

import java.util.*;

public class LayeredGraphIterator<T> implements Iterator<List<Node<T>>> {
    private final List<Node<T>> visitedNodes;

    private List<Node<T>> currentLayer;

    public LayeredGraphIterator(Node<T> startNode) {
        this.currentLayer = new LinkedList<>() {
            {
                add(startNode);
            }
        };
        visitedNodes = new LinkedList<>();
    }

    @Override
    public boolean hasNext() {
        return currentLayer.stream().anyMatch(this::nodeHasNonVisitedConnections);
    }

    private boolean nodeHasNonVisitedConnections(Node<T> node) {
        return node.getConnections().stream().anyMatch(n -> !visitedNodes.contains(n));
    }

    @Override
    public List<Node<T>> next() {
        if (visitedNodes.isEmpty()) {
            visitedNodes.addAll(currentLayer);
            return currentLayer;
        }
        var newLayer = new LinkedList<Node<T>>();
        for (var node : currentLayer) {
            var filteredConnections = filterVisited(node.getConnections());
            visitedNodes.addAll(filteredConnections);
            newLayer.addAll(filteredConnections);
        }
        currentLayer = newLayer;
        if (currentLayer.isEmpty()) {
            throw new NoSuchElementException();
        }
        return currentLayer;
    }

    private List<Node<T>> filterVisited(List<Node<T>> nodes) {
        return nodes.stream()
                .filter(n -> !visitedNodes.contains(n))
                .toList();
    }
}
