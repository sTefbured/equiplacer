package com.kotikov.equiplacer.client.desktop.util;

import com.kotikov.equiplacer.core.model.EquipmentOptimum;
import com.kotikov.equiplacer.core.model.enums.ReplacementDecision;
import org.apache.commons.lang3.tuple.Pair;

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

    public static List<Pair<Double, String>> convert(EquipmentOptimum solution) {
        if (solution.getNextOptimums().isEmpty()) {
            return new LinkedList<>() {{
                add(Pair.of(0.0, ""));
            }};
        }
        var nextOptimums = solution.getNextOptimums();
        var resultList = new LinkedList<String>();
        nextOptimums.forEach(entry -> {
            var convertedList = convert(entry.getValue()).stream()
                    .map(item -> REPLACEMENT_DECISION_CHARACTER_MAP.get(entry.getKey()) + "-" + entry.getValue().getAge() + " " + item.getRight())
                    .toList();
            resultList.addAll(convertedList);
        });
        return resultList.stream().map(str -> Pair.of(solution.getFunctionValue(), str)).toList();
    }
}
