package com.kotikov.equiplacer.graph;

import java.util.Iterator;
import java.util.Map;

public class GraphIterator<T> implements Iterator<Node<T>> {
    private final Iterator<Map.Entry<Integer, Node<T>>> poolIterator;

    GraphIterator(Graph<T> graph) {
        var nodesPool = graph.getNodeContainer().getNodesPool();
        poolIterator = nodesPool.iterator();
    }

    @Override
    public boolean hasNext() {
        return poolIterator.hasNext();
    }

    @Override
    public Node<T> next() {
        var entry = poolIterator.next();
        return entry.getValue();
    }
}
