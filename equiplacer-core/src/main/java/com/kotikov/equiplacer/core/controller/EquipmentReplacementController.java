package com.kotikov.equiplacer.core.controller;

import com.kotikov.equiplacer.core.model.EquipmentInformation;
import com.kotikov.equiplacer.core.model.dto.EquipmentOptimum;
import com.kotikov.equiplacer.core.model.dto.EquipmentReplacementDTO;
import com.kotikov.equiplacer.core.service.EquipmentGraphService;
import com.kotikov.equiplacer.core.service.EquipmentReplacementService;
import com.kotikov.equiplacer.graph.Graph;

public class EquipmentReplacementController {
    private final EquipmentReplacementService equipmentReplacementService;
    private final EquipmentGraphService equipmentGraphService;

    public EquipmentReplacementController() {
        equipmentReplacementService = new EquipmentReplacementService();
        equipmentGraphService = new EquipmentGraphService();
    }

    public Graph<Integer> getEquipmentGraph(EquipmentInformation equipmentInformation) {
        var yearsCount = equipmentInformation.getYearsCount();
        var maxAge = equipmentInformation.getMaxAge();
        var newEquipmentAges = equipmentInformation.getEquipmentAgesPerYear();
        return equipmentGraphService.getEquipmentGraph(yearsCount, maxAge, newEquipmentAges);
    }

    public EquipmentOptimum getEquipmentOptimumSolution(EquipmentInformation equipmentInformation) {
        var graph = getEquipmentGraph(equipmentInformation);
        return getEquipmentOptimumSolution(equipmentInformation, graph);
    }

    public EquipmentOptimum getEquipmentOptimumSolution(EquipmentInformation equipmentInformation, Graph<Integer> graph) {
        var equipmentReplacementDto = new EquipmentReplacementDTO(equipmentInformation, graph);
        return equipmentReplacementService.getAnswerSequences(equipmentReplacementDto);
    }
}