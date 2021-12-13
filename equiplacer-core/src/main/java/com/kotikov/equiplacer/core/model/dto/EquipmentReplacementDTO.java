package com.kotikov.equiplacer.core.model.dto;

import com.kotikov.equiplacer.core.model.EquipmentInformation;
import com.kotikov.equiplacer.graph.Graph;

public class EquipmentReplacementDTO {
    private EquipmentInformation equipmentInformation;
    private Graph<Integer> replacementGraph;

    public EquipmentReplacementDTO(EquipmentInformation equipmentInformation, Graph<Integer> replacementGraph) {
        this.equipmentInformation = equipmentInformation;
        this.replacementGraph = replacementGraph;
    }

    public EquipmentInformation getEquipmentInformation() {
        return equipmentInformation;
    }

    public void setEquipmentInformation(EquipmentInformation equipmentInformation) {
        this.equipmentInformation = equipmentInformation;
    }

    public Graph<Integer> getReplacementGraph() {
        return replacementGraph;
    }

    public void setReplacementGraph(Graph<Integer> replacementGraph) {
        this.replacementGraph = replacementGraph;
    }
}
