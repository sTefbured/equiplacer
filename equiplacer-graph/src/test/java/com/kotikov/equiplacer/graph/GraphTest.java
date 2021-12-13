package com.kotikov.equiplacer.graph;

import com.kotikov.equiplacer.graph.iterator.DefaultGraphIterator;
import com.kotikov.equiplacer.graph.iterator.LayeredGraphIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private static final int LAYERS_COUNT = 5;
    private static final int NODES_COUNT = 11;
    private static final List<List<Integer>> LAYERS_VALUES = new ArrayList<>() {
        {
            add(List.of(1));
            add(List.of(2, 3, 4, 5));
            add(List.of(6, 7, 8));
            add(List.of(9));
            add(List.of(10, 11));
        }
    };
    private static final int[][] ADJACENCY_MATRIX = new int[][]{
            {0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0}
    };
    private static final List<Integer> DATA = new ArrayList<>(NODES_COUNT) {
        {
            for (int i = 0; i < NODES_COUNT; i++) {
                add(i + 1);
            }
        }
    };

    private Graph<Integer> graph;

    @BeforeEach
    public void initialize() {
        graph = new Graph<>(ADJACENCY_MATRIX, DATA);
    }

    @Test
    void iterator_next() {
        var iterator = graph.iterator();
        assertInstanceOf(DefaultGraphIterator.class, iterator, "Incorrect iterator type. Check if the test is out of date");
        for (int i = 0; i < NODES_COUNT; i++) {
            var node = iterator.next();
            assertValidNode(node, i);
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    private void assertValidNode(Node<Integer> node, int i) {
        assertEquals(DATA.get(i), node.getData());
        for (int j = 0; j < NODES_COUNT; j++) {
            if (ADJACENCY_MATRIX[i][j] == 0) {
                continue;
            }
            int finalJ = j;
            assertTrue(node.getConnections().stream().anyMatch(n -> n.getData().equals(DATA.get(finalJ))));
        }
    }

    @Test
    void iterator_hasNext() {
        var iterator = graph.iterator();
        assertInstanceOf(DefaultGraphIterator.class, iterator, "Incorrect iterator type. Check if the test is out of date");
        for (int i = 0; i < NODES_COUNT; i++) {
            assertTrue(iterator.hasNext(), "Iterator.hasNext() returned incorrect value");
            iterator.next();
        }
        assertFalse(iterator.hasNext(), "Iterator.hasNext() returned incorrect value");
    }

    @Test
    void layeredIterator_hasNext() {
        var iterator = graph.layeredIterator();
        assertInstanceOf(LayeredGraphIterator.class, iterator, "Incorrect iterator type. Check if the test is out of date");
        for (int i = 0; i < LAYERS_COUNT; i++) {
            assertTrue(iterator.hasNext(), "Iterator.hasNext() returned incorrect value");
            iterator.next();
        }
        assertFalse(iterator.hasNext(), "Iterator.hasNext() returned incorrect value");
    }

    @Test
    void layeredIterator_next() {
        var iterator = graph.layeredIterator();
        assertInstanceOf(LayeredGraphIterator.class, iterator, "Incorrect iterator type. Check if the test is out of date");
        for (int i = 0; i < LAYERS_COUNT; i++) {
            var nodes = iterator.next();
            assertEquals(LAYERS_VALUES.get(i), nodes.stream().map(Node::getData).toList());
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}