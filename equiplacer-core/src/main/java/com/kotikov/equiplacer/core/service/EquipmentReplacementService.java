package com.kotikov.equiplacer.core.service;

import com.kotikov.equiplacer.core.model.EquipmentInformation;
import com.kotikov.equiplacer.core.model.dto.EquipmentOptimum;
import com.kotikov.equiplacer.core.model.dto.EquipmentReplacementDTO;
import com.kotikov.equiplacer.core.model.enums.ReplacementDecision;
import com.kotikov.equiplacer.graph.Node;

import java.util.*;

public class EquipmentReplacementService {
    private EquipmentInformation equipmentInformation;

    public EquipmentReplacementService() {
    }

    public EquipmentOptimum getAnswerSequences(EquipmentReplacementDTO equipmentReplacementDTO) {
        var graph = equipmentReplacementDTO.getReplacementGraph();
        equipmentInformation = equipmentReplacementDTO.getEquipmentInformation();
        var ageLayers = new LinkedList<List<Node<Integer>>>();
        var layeredIterator = graph.layeredIterator();
        layeredIterator.forEachRemaining(ageLayers::add);
        var resultMap = calculate(ageLayers, 1).getFirst();
        return resultMap.values().stream().findFirst().orElseThrow();
    }

    //TODO: refactor
    private LinkedList<Map<Integer, EquipmentOptimum>> calculate(LinkedList<List<Node<Integer>>> ageLayers, int year) {
        var currentAgeLayer = ageLayers.pollFirst();
        if (year > equipmentInformation.getYearsCount()) {
            return new LinkedList<>();
        }
        var resultStack = calculate(ageLayers, year + 1);
        var nextResultLayer = resultStack.peek();
        var currentResultLayer = new LinkedHashMap<Integer, EquipmentOptimum>();
        for (var ageNode : Objects.requireNonNull(currentAgeLayer)) {
            var prevFuncValSave = nextResultLayer != null ? nextResultLayer.get(ageNode.getData() + 1).getFunctionValue() : 0;
            var prevFuncValReplace = nextResultLayer != null ? nextResultLayer.get(equipmentInformation.getEquipmentAgesPerYear().get(year - 1)).getFunctionValue() : 0;
            var replaceIncome = getReplaceIncome(ageNode.getData(), year, prevFuncValReplace);
            var saveIncome = getSaveIncome(ageNode.getData(), equipmentInformation.getMaxAge(), prevFuncValSave);
            var optimum = findOptimum(ageNode.getData(), year, replaceIncome, saveIncome, nextResultLayer);
            currentResultLayer.put(ageNode.getData(), optimum);
        }
        resultStack.push(currentResultLayer);
        return resultStack;
    }

    private EquipmentOptimum findOptimum(int age, int year, double replaceIncome, double saveIncome,
                                         Map<Integer, EquipmentOptimum> nextResultLayer) {
        var optimum = new EquipmentOptimum();
        if (saveIncome > replaceIncome) {
            optimum.setFunctionValue(saveIncome);
            optimum.setReplacementDecision(ReplacementDecision.KEEP);
            if (nextResultLayer != null) {
                optimum.getNextOptimums().add(nextResultLayer.get(age + 1));
            }
        } else if (saveIncome < replaceIncome) {
            optimum.setFunctionValue(replaceIncome);
            optimum.setReplacementDecision(ReplacementDecision.REPLACE);
            var newEquipmentAge = equipmentInformation.getEquipmentAgesPerYear().get(year - 1);
            if (nextResultLayer != null) {
                optimum.getNextOptimums().add(nextResultLayer.get(newEquipmentAge));
            }
        } else {
            optimum.setFunctionValue(saveIncome);
            optimum.setReplacementDecision(ReplacementDecision.BOTH);
            var newEquipmentAge = equipmentInformation.getEquipmentAgesPerYear().get(year - 1);
            if (nextResultLayer != null) {
                optimum.getNextOptimums().add(nextResultLayer.get(newEquipmentAge));
                optimum.getNextOptimums().add(nextResultLayer.get(age + 1));
            }
        }
        return optimum;
    }

    private double getReplaceIncome(int age, int year, double lastFunctionValue) {
        return equipmentInformation.getIncomes().get(0)
                + equipmentInformation.getResidualCosts().get(age)
                - equipmentInformation.getEquipmentCosts().get(year - 1)
                - equipmentInformation.getMaintenanceCosts().get(0)
                + lastFunctionValue;
    }

    private double getSaveIncome(int age, int maxAge, double lastFunctionValue) {
        if (age == maxAge) {
            return Double.NEGATIVE_INFINITY;
        }
        return equipmentInformation.getIncomes().get(age)
                - equipmentInformation.getMaintenanceCosts().get(age)
                + lastFunctionValue;
    }
}
