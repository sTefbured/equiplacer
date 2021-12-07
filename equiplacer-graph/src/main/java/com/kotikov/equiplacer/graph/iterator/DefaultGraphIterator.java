package com.kotikov.equiplacer.graph.iterator;

import com.kotikov.equiplacer.graph.Graph;
import com.kotikov.equiplacer.graph.Node;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DefaultGraphIterator<T> implements Iterator<Node<T>> {
    private final Iterator<Map.Entry<Integer, Node<T>>> poolIterator;

    public DefaultGraphIterator(Graph<T> graph) {
        this(graph.getNodeContainer().getNodesPool());
    }

    public DefaultGraphIterator(Set<Map.Entry<Integer, Node<T>>> nodesPool) {
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
