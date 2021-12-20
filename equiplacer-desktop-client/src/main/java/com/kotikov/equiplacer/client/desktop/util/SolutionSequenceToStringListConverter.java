package com.kotikov.equiplacer.client.desktop.util;

import com.kotikov.equiplacer.core.model.EquipmentOptimum;
import com.kotikov.equiplacer.core.model.enums.ReplacementDecision;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SolutionSequenceToStringListConverter {
    private final static Map<ReplacementDecision, Character> REPLACEMENT_DECISION_CHARACTER_MAP = new HashMap<>() {
        {
            put(ReplacementDecision.KEEP, 'K');
            put(ReplacementDecision.REPLACE, 'R');
        }
    };

    public static List<String> convert(EquipmentOptimum solution) {
        if (solution.getNextOptimums().isEmpty()) {
            return new LinkedList<>() {{
                add("");
            }};
        }
        var nextOptimums = solution.getNextOptimums();
        var resultList = new LinkedList<String>();
        nextOptimums.forEach(entry -> {
            var convertedList = convert(entry.getValue()).stream()
                    .map(str -> REPLACEMENT_DECISION_CHARACTER_MAP.get(entry.getKey()) + "-" + entry.getValue().getAge() + " " + str)
                    .toList();
            resultList.addAll(convertedList);
        });
        return resultList;
    }
}
