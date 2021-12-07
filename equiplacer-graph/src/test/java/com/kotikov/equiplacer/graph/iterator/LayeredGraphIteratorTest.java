package com.kotikov.equiplacer.graph.iterator;

import com.kotikov.equiplacer.graph.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LayeredGraphIteratorTest {
    private LayeredGraphIterator<Integer> iterator;
    private List<List<Node<Integer>>> layers;

    @BeforeEach
    public void initialize() {
        layers = new ArrayList<>(5);
        List<Node<Integer>> nodes = new ArrayList<>(11);
        for (int i = 0; i < 11; i++) {
            nodes.add(new Node<>(i + 1));
        }
        layers.add(List.of(nodes.get(0)));
        layers.add(List.of(nodes.get(1), nodes.get(2), nodes.get(3), nodes.get(4)));
        layers.add(List.of(nodes.get(5), nodes.get(6), nodes.get(7)));
        layers.add(List.of(nodes.get(8)));
        layers.add(List.of(nodes.get(9), nodes.get(10)));

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

        iterator = new LayeredGraphIterator<>(nodes.get(0));
    }

    @Test
    public void hasNext() {
        for (int i = 0; i < layers.size(); i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    public void next() {
        for (var expectedLayer : layers) {
            var actualLayer = iterator.next();
            assertEquals(expectedLayer, actualLayer);
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}