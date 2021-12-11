package com.kotikov.equiplacer.core.service;

import com.kotikov.equiplacer.graph.Graph;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class EquipmentGraphService {
    public EquipmentGraphService() {

    }

    public Graph<Integer> getEquipmentGraph(int yearsCount, int maxAge, int currentAge, List<Integer> newEquipmentAges) {
        var nodesPoolMap = new LinkedHashMap<Pair<Integer, Integer>, int[]>();
        fillNodesPool(nodesPoolMap, maxAge, currentAge, 1, yearsCount, newEquipmentAges);

        var nodesPool = nodesPoolMap.entrySet().stream().toList();
        nodesPool.forEach(entry -> entry.setValue(new int[nodesPool.size()]));
        setConnections(nodesPool, maxAge, yearsCount, newEquipmentAges);

        var adjacencyMatrix = nodesPool.stream().map(Map.Entry::getValue).toArray(int[][]::new);
        var data = nodesPoolMap.keySet().stream().map(Pair::getLeft).toList();
        return new Graph<>(adjacencyMatrix, data);
    }

    private void fillNodesPool(Map<Pair<Integer, Integer>, int[]> nodesPool, int maxAge, int currentAge,
                               int currentYear, int yearsCount, List<Integer> newEquipmentAges) {
        if (currentAge > maxAge) {
            throw new IllegalArgumentException("Current age must be equal or less than max age");
        }
        var key = Pair.of(currentAge, currentYear);
        if (nodesPool.containsKey(key)) {
            return;
        }
        nodesPool.put(key, null);
        if (currentYear > yearsCount) {
            return;
        }
        if (currentAge < maxAge) {
            fillNodesPool(nodesPool, maxAge, currentAge + 1, currentYear + 1, yearsCount, newEquipmentAges);
        }
        fillNodesPool(nodesPool, maxAge, newEquipmentAges.get(currentYear), currentYear + 1, yearsCount, newEquipmentAges);
    }

    private void setConnections(List<Map.Entry<Pair<Integer, Integer>, int[]>> nodesPool,
                                int maxAge, int yearsCount, List<Integer> newEquipmentAges) {
        nodesPool.forEach(entry -> {
            var year = entry.getKey().getRight();
            if (year > yearsCount) {
                return;
            }
            var age = entry.getKey().getLeft();
            if (age < maxAge) {
                var key = Pair.of(age + 1, year + 1);
                var connection = nodesPool.stream().filter(e -> e.getKey().equals(key)).findFirst();
                connection.ifPresent(c -> entry.getValue()[nodesPool.indexOf(c)] = 1);
            }
            var key = Pair.of(newEquipmentAges.get(year), year + 1);
            var connection = nodesPool.stream().filter(e -> e.getKey().equals(key)).findFirst();
            connection.ifPresent(c -> entry.getValue()[nodesPool.indexOf(c)] = 1);
        });
    }
}
