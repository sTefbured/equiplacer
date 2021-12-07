package com.kotikov.equiplacer.graph;

import java.util.*;

public class NodeContainer<T> {
    private int nextId = 1;
    private final Map<Integer, Node<T>> nodesPool;

    public NodeContainer() {
        nodesPool = new LinkedHashMap<>();
    }

    public void addRange(List<T> data, int[][] adjacencyMatrix) {
        data.forEach(dataEntry -> {
            nodesPool.put(nextId, new Node<>(dataEntry));
            nextId++;
        });
        var adjacencyIterator = Arrays.stream(adjacencyMatrix).iterator();
        nodesPool.forEach((key, node) -> {
            var connections = adjacencyIterator.next();
            for (int i = 0; i < connections.length; i++) {
                if (connections[i] > 0) {
                    node.getConnections().add(nodesPool.get(i + 1));
                }
            }
        });
    }

    public Node<T> getNodeById(int id) {
        return nodesPool.get(id);
    }

    public Set<Map.Entry<Integer, Node<T>>> getNodesPool() {
        return Collections.unmodifiableSet(nodesPool.entrySet());
    }
}
