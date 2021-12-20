package com.kotikov.equiplacer.core.service;

import com.kotikov.equiplacer.core.model.dto.EquipmentGraphDTO;
import com.kotikov.equiplacer.graph.Graph;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class EquipmentGraphService {
    public EquipmentGraphService() {

    }

    public Graph<Integer> getEquipmentGraph(EquipmentGraphDTO equipmentGraphDTO) {
        if (equipmentGraphDTO.getMaxNewEquipmentAge() >= equipmentGraphDTO.getMaxAge()) {
            throw new IllegalArgumentException("Max new equipment age must be less than max equipment age");
        }
        var nodesPoolMap = new LinkedHashMap<Pair<Integer, Integer>, int[]>();
        fillNodesPool(nodesPoolMap, equipmentGraphDTO.getFirstYearAge(), 1, equipmentGraphDTO);

        var nodesPool = nodesPoolMap.entrySet().stream().toList();
        nodesPool.forEach(entry -> entry.setValue(new int[nodesPool.size()]));
        setConnections(nodesPool, equipmentGraphDTO);

        var adjacencyMatrix = nodesPool.stream().map(Map.Entry::getValue).toArray(int[][]::new);
        var data = nodesPoolMap.keySet().stream().map(Pair::getLeft).toList();
        return new Graph<>(adjacencyMatrix, data);
    }

    private void fillNodesPool(Map<Pair<Integer, Integer>, int[]> nodesPool, int currentAge,
                               int currentYear, EquipmentGraphDTO equipmentGraphDTO) {
        if (currentAge > equipmentGraphDTO.getMaxAge()) {
            throw new IllegalArgumentException("Current age must be equal or less than max age");
        }
        var key = Pair.of(currentAge, currentYear);
        if (nodesPool.containsKey(key)) {
            return;
        }
        nodesPool.put(key, null);
        if (currentYear > equipmentGraphDTO.getYearsCount()) {
            return;
        }
        if (currentAge < equipmentGraphDTO.getMaxAge()) {
            fillNodesPool(nodesPool, currentAge + 1, currentYear + 1, equipmentGraphDTO);
        }
        for (int age = 1; age <= equipmentGraphDTO.getMaxNewEquipmentAge(); age++) {
            fillNodesPool(nodesPool, age, currentYear + 1, equipmentGraphDTO);
        }
    }

    private void setConnections(List<Map.Entry<Pair<Integer, Integer>, int[]>> nodesPool,
                                EquipmentGraphDTO equipmentGraphDTO) {
        nodesPool.forEach(entry -> {
            var year = entry.getKey().getRight();
            if (year > equipmentGraphDTO.getYearsCount()) {
                return;
            }
            var age = entry.getKey().getLeft();
            if (age < equipmentGraphDTO.getMaxAge()) {
                var key = Pair.of(age + 1, year + 1);
                var connection = nodesPool.stream().filter(e -> e.getKey().equals(key)).findFirst();
                connection.ifPresent(c -> entry.getValue()[nodesPool.indexOf(c)] = 1);
            }
            for (int newAge = 1; newAge <= equipmentGraphDTO.getMaxNewEquipmentAge(); newAge++) {
                var key = Pair.of(newAge, year + 1);
                var connection = nodesPool.stream().filter(e -> e.getKey().equals(key)).findFirst();
                connection.ifPresent(c -> entry.getValue()[nodesPool.indexOf(c)] = 1);
            }
        });
    }
}
