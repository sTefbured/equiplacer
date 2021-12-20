package com.kotikov.equiplacer.core.service;

import com.kotikov.equiplacer.core.model.EquipmentInformation;
import com.kotikov.equiplacer.core.model.EquipmentOptimum;
import com.kotikov.equiplacer.core.model.dto.EquipmentReplacementDTO;
import com.kotikov.equiplacer.core.model.enums.ReplacementDecision;
import com.kotikov.equiplacer.graph.Node;
import org.apache.commons.lang3.tuple.Pair;

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

    private LinkedList<Map<Integer, EquipmentOptimum>> calculate(LinkedList<List<Node<Integer>>> ageLayers, int year) {
        var currentAgeLayer = ageLayers.pollFirst();
        if (year > equipmentInformation.getYearsCount()) {
            return new LinkedList<>();
        }
        var resultStack = calculate(ageLayers, year + 1);
        var nextResultLayer = resultStack.peek();
        var currentResultLayer = new LinkedHashMap<Integer, EquipmentOptimum>();
        for (var ageNode : Objects.requireNonNull(currentAgeLayer)) {
            setOptimums(currentResultLayer, ageNode.getData(), year, nextResultLayer);
        }
        resultStack.push(currentResultLayer);
        return resultStack;
    }

    private void setOptimums(Map<Integer, EquipmentOptimum> currentResultLayer, int age, int year,
                             Map<Integer, EquipmentOptimum> nextResultLayer) {
        EquipmentOptimum optimum;
        var saveIncome = getSaveIncome(age, year, equipmentInformation.getMaxAge(), nextResultLayer);
        var maxReplaceIncome = Double.NEGATIVE_INFINITY;
        var lastReplaceFunctionValue = 0.0;
        for (int i = 1; i <= equipmentInformation.getMaxNewEquipmentAge(); i++) {
            var replaceIncome = getReplaceIncome(age, year, i, nextResultLayer);
            if (replaceIncome.getLeft() < maxReplaceIncome) {
                continue;
            }
            if (replaceIncome.getLeft() > maxReplaceIncome) {
                maxReplaceIncome = replaceIncome.getLeft();
            }
            lastReplaceFunctionValue = replaceIncome.getRight();
        }
        if (maxReplaceIncome < saveIncome) {
            optimum = createSaveOptimum(saveIncome, age, nextResultLayer, currentResultLayer);
        } else if (saveIncome == maxReplaceIncome) {
            optimum = createBothOptimum(saveIncome, age, nextResultLayer, lastReplaceFunctionValue);
        } else {
            optimum = createReplaceOptimum(maxReplaceIncome, age, nextResultLayer, lastReplaceFunctionValue);
        }
        currentResultLayer.put(age, optimum);
    }

    private EquipmentOptimum createReplaceOptimum(double replaceIncome,
                                                  int age,
                                                  Map<Integer, EquipmentOptimum> nextResultLayer,
                                                  double lastReplaceFunctionValue) {
        var optimum = new EquipmentOptimum(replaceIncome, age);
        if (nextResultLayer != null) {
            var optimums = nextResultLayer.values().stream()
                    .filter(entry -> entry.getFunctionValue() == lastReplaceFunctionValue)
                    .map(value -> Map.entry(ReplacementDecision.REPLACE, value))
                    .toList();
            optimum.getNextOptimums().addAll(optimums);
        } else {
            optimum.getNextOptimums().add(Map.entry(ReplacementDecision.REPLACE, new EquipmentOptimum(0, 1)));
        }
        return optimum;
    }

    private EquipmentOptimum createBothOptimum(double income,
                                               int age,
                                               Map<Integer, EquipmentOptimum> nextResultLayer,
                                               double lastReplaceFunctionValue) {
        var optimum = new EquipmentOptimum(income, age);
        if (nextResultLayer != null) {
            optimum.getNextOptimums().add(Map.entry(ReplacementDecision.KEEP, nextResultLayer.get(age + 1)));
            var optimums = nextResultLayer.values().stream()
                    .filter(entry -> entry.getFunctionValue() == lastReplaceFunctionValue)
                    .map(value -> Map.entry(ReplacementDecision.REPLACE, value))
                    .toList();
            optimum.getNextOptimums().addAll(optimums);
        } else {
            optimum.getNextOptimums().add(Map.entry(ReplacementDecision.KEEP, new EquipmentOptimum(0, age + 1)));
            optimum.getNextOptimums().add(Map.entry(ReplacementDecision.REPLACE, new EquipmentOptimum(0, 1)));
        }
        return optimum;
    }

    private EquipmentOptimum createSaveOptimum(double saveIncome,
                                               int age,
                                               Map<Integer, EquipmentOptimum> nextResultLayer,
                                               Map<Integer, EquipmentOptimum> currentResultLayer) {
        var optimum = new EquipmentOptimum(saveIncome, age);
        if (nextResultLayer != null) {
            optimum.getNextOptimums().add(Map.entry(ReplacementDecision.KEEP, nextResultLayer.get(age + 1)));
        } else {
            optimum.getNextOptimums().add(Map.entry(ReplacementDecision.KEEP, new EquipmentOptimum(0, age + 1)));
        }
        currentResultLayer.put(age, optimum);
        return optimum;
    }

    private Pair<Integer, Double> getReplaceIncome(int age,
                                                   int year,
                                                   int newEquipmentAge,
                                                   Map<Integer, EquipmentOptimum> nextResultLayer) {
        var lastFunctionValue = nextResultLayer != null
                ? nextResultLayer.get(newEquipmentAge).getFunctionValue()
                : 0;
        int result = equipmentInformation.getIncomes().get(newEquipmentAge - 1);
        result += equipmentInformation.getResidualCosts().get(age);
        result += -equipmentInformation.getResidualCosts().get(newEquipmentAge - 1) + lastFunctionValue;
        result -= equipmentInformation.getMaintenanceCosts().get(newEquipmentAge - 1);
        if (equipmentInformation.isSellLastYearEquipmentOn() && equipmentInformation.getYearsCount() == year) {
            result += equipmentInformation.getResidualCosts().get(newEquipmentAge);
        }
        return Pair.of(result, lastFunctionValue);
    }

    private double getSaveIncome(int age, int year, int maxAge, Map<Integer, EquipmentOptimum> nextResultLayer) {
        if (age == maxAge) {
            return Double.NEGATIVE_INFINITY;
        }
        var lastFunctionValue = nextResultLayer != null ? nextResultLayer.get(age + 1).getFunctionValue() : 0;
        var result = equipmentInformation.getIncomes().get(age) - equipmentInformation.getMaintenanceCosts().get(age);
        result += lastFunctionValue;
        if (equipmentInformation.isSellLastYearEquipmentOn() && equipmentInformation.getYearsCount() == year) {
            result += equipmentInformation.getResidualCosts().get(age + 1);
        }
        return result;
    }
}
