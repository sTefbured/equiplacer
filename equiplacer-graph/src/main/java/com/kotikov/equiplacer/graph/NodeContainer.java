package com.kotikov.equiplacer.graph;

import java.util.*;
import java.util.function.Function;

public class NodeContainer<T> {
    private int nextId = 1;
    private Map<Integer, Node<T>> nodesPool;

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

    public <E> NodeContainer<E> map(Function<T, E> mapper) {
        var entrySet = nodesPool.entrySet();
        var newNodesPool = new LinkedHashMap<Integer, Node<E>>();
        entrySet.forEach(entry -> {
            var newValue = new Node<>(mapper.apply(entry.getValue().getData()));
            newNodesPool.put(entry.getKey(), newValue);
        });

        setConnections(entrySet, newNodesPool);

        var result = new NodeContainer<E>();
        result.nextId = nextId;
        result.nodesPool = newNodesPool;
        return result;
    }

    private <E> void setConnections(Set<Map.Entry<Integer, Node<T>>> oldEntrySet,
                                    Map<Integer, Node<E>> newNodesPool) {
        for (var entry : oldEntrySet) {
            var newNode = newNodesPool.get(entry.getKey());
            var connections = entry.getValue().getConnections();
            for (var connection : connections) {
                var key = oldEntrySet.stream()
                        .filter(e -> e.getValue().equals(connection))
                        .findFirst()
                        .orElseThrow()
                        .getKey();
                newNode.getConnections().add(newNodesPool.get(key));
            }
        }
    }

    public Set<Map.Entry<Integer, Node<T>>> getNodesPool() {
        return Collections.unmodifiableSet(nodesPool.entrySet());
    }
}
