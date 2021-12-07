package com.kotikov.equiplacer.graph;

import java.util.LinkedList;
import java.util.List;

public class Node<T> {
    private final List<Node<T>> connections;
    private T data;

    public Node(T data) {
        this.data = data;
        connections = new LinkedList<>();
    }

    public Node(List<Node<T>> connections, T data) {
        this.connections = connections;
        this.data = data;
    }

    public List<Node<T>> getConnections() {
        return connections;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
