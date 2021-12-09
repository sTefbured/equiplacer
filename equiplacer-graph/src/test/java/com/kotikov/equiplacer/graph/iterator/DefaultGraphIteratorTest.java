package com.kotikov.equiplacer.graph.iterator;

import com.kotikov.equiplacer.graph.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DefaultGraphIteratorTest {
    private List<Node<Integer>> nodes;

    private DefaultGraphIterator<Integer> iterator;

    @BeforeEach
    public void initialize() {
        nodes = new ArrayList<>(11);
        for (int i = 0; i < 11; i++) {
            nodes.add(new Node<>(i + 1));
        }

        nodes.get(0).getConnections().add(nodes.get(1));
        nodes.get(0).getConnections().add(nodes.get(2));
        nodes.get(0).getConnections().add(nodes.get(3));
        nodes.get(0).getConnections().add(nodes.get(4));

        nodes.get(1).getConnections().add(nodes.get(0));
        nodes.get(1).getConnections().add(nodes.get(3));
        nodes.get(1).getConnections().add(nodes.get(5));

        nodes.get(2).getConnections().add(nodes.get(0));
        nodes.get(2).getConnections().add(nodes.get(4));

        nodes.get(3).getConnections().add(nodes.get(0));
        nodes.get(3).getConnections().add(nodes.get(1));
        nodes.get(3).getConnections().add(nodes.get(5));

        nodes.get(4).getConnections().add(nodes.get(0));
        nodes.get(4).getConnections().add(nodes.get(2));
        nodes.get(4).getConnections().add(nodes.get(6));
        nodes.get(4).getConnections().add(nodes.get(7));

        nodes.get(5).getConnections().add(nodes.get(1));
        nodes.get(5).getConnections().add(nodes.get(3));
        nodes.get(5).getConnections().add(nodes.get(8));

        nodes.get(6).getConnections().add(nodes.get(4));

        nodes.get(7).getConnections().add(nodes.get(4));
        nodes.get(7).getConnections().add(nodes.get(8));

        nodes.get(8).getConnections().add(nodes.get(5));
        nodes.get(8).getConnections().add(nodes.get(7));
        nodes.get(8).getConnections().add(nodes.get(9));
        nodes.get(8).getConnections().add(nodes.get(10));

        nodes.get(9).getConnections().add(nodes.get(8));
        nodes.get(9).getConnections().add(nodes.get(10));

        nodes.get(10).getConnections().add(nodes.get(8));
        nodes.get(10).getConnections().add(nodes.get(9));

        final int[] id = {1};
        Set<Map.Entry<Integer, Node<Integer>>> nodesPool = nodes.stream()
                .map(node -> {
                    var entry = Map.entry(id[0], node);
                    id[0]++;
                    return entry;
                })
                .collect(Collectors.toSet());
        var sortedPool = new TreeSet<Map.Entry<Integer, Node<Integer>>>((e1, e2) -> {
            if (e1.getKey() > e2.getKey()) {
                return 1;
            } else if (e1.getKey() < e2.getKey()) {
                return -1;
            }
            return 0;
        });
        sortedPool.addAll(nodesPool);
        iterator = new DefaultGraphIterator<>(sortedPool);
    }

    @Test
    public void hasNext() {
        for (int i = 0; i < nodes.size(); i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    public void next() {
        for (var expectedNode : nodes) {
            var actualNode = iterator.next();
            assertEquals(expectedNode, actualNode,
                    "Expected: " + expectedNode.getData() + " Actual: " + actualNode.getData());
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}