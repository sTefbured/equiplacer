package com.kotikov.equiplacer.core.service;

import com.kotikov.equiplacer.core.model.EquipmentInformation;
import com.kotikov.equiplacer.core.model.dto.EquipmentReplacementDTO;
import com.kotikov.equiplacer.graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static com.kotikov.equiplacer.core.model.enums.ReplacementDecision.KEEP;
import static com.kotikov.equiplacer.core.model.enums.ReplacementDecision.REPLACE;
import static org.junit.jupiter.api.Assertions.*;

class EquipmentReplacementServiceTest {
    private EquipmentReplacementService service;

    @BeforeEach
    public void initialize() {
        service = new EquipmentReplacementService();
    }

    @Test
    public void getAnswerSequences_NewEquipmentIsNew() {
        var equipmentInformation = EquipmentInformation.builder()
                .setEquipmentCosts(List.of(100000, 100000, 100000, 100000))
                .setMaxNewEquipmentAge(1)
                .setIncomes(List.of(20000, 19000, 18500, 17200, 15500, 14000, 12200))
                .setResidualCosts(List.of(100000, 80000, 60000, 50000, 30000, 10000, 5000))
                .setMaintenanceCosts(List.of(200, 600, 1200, 1500, 1700, 1800, 2200))
                .setCurrentAge(3)
                .setMaxAge(6)
                .setYearsCount(4)
                .build();
        var graph = createGraph_NewEquipmentIsNew();
        var equipmentDto = new EquipmentReplacementDTO(equipmentInformation, graph);
        var current = service.getAnswerSequences(equipmentDto);
        var expectationIterator = List.of(REPLACE, KEEP, KEEP, KEEP).iterator();
        while (current != null) {
            var expected = expectationIterator.next();
            var actual = current.getReplacementDecision();
            assertEquals(expected, actual);
            current = current.getNextOptimums().isEmpty() ? null : current.getNextOptimums().get(0);
        }
    }

    private Graph<Integer> createGraph_NewEquipmentIsNew() {
        var adjacencyMatrix = new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        var data = List.of(3, 4, 5, 6, 1, 1, 2, 1, 2, 3, 1, 2, 3, 4);
        return new Graph<>(adjacencyMatrix, data);
    }

    @Test
    public void getAnswerSequences_NewEquipmentIsNotNew() {
        var equipmentInformation = EquipmentInformation.builder()
                .setEquipmentCosts(List.of(100000, 100000, 100000, 100000))
                .setMaxNewEquipmentAge(2)
                .setIncomes(List.of(20000, 19000, 18500, 17200, 15500, 14000, 12200))
                .setResidualCosts(List.of(100000, 80000, 60000, 50000, 30000, 10000, 5000))
                .setMaintenanceCosts(List.of(200, 600, 1200, 1500, 1700, 1800, 2200))
                .setCurrentAge(3)
                .setMaxAge(6)
                .setYearsCount(4)
                .build();
        var graph = createGraph_NewEquipmentIsNotNew();
        var equipmentDto = new EquipmentReplacementDTO(equipmentInformation, graph);
        var current = service.getAnswerSequences(equipmentDto);
        var expectations = List.of(REPLACE, KEEP, KEEP, KEEP);
        for (var expected : expectations) {
            var actual = Objects.requireNonNull(current).getReplacementDecision();
            assertEquals(expected, actual);
            current = current.getNextOptimums().isEmpty() ? null : current.getNextOptimums().get(0);
        }
    }

    private Graph<Integer> createGraph_NewEquipmentIsNotNew() {
        var adjacencyMatrix = new int[][] {
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        var data = List.of(3, 4, 5, 6, 1, 2, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 4, 5);
        return new Graph<>(adjacencyMatrix, data);
    }
}