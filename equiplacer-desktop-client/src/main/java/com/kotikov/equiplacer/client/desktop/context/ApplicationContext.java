package com.kotikov.equiplacer.client.desktop.context;

import com.kotikov.equiplacer.client.desktop.view.ClientFrame;
import com.kotikov.equiplacer.core.controller.EquipmentReplacementController;
import com.kotikov.equiplacer.core.model.EquipmentInformation;
import com.kotikov.equiplacer.core.model.EquipmentOptimum;
import com.kotikov.equiplacer.graph.Graph;

public class ApplicationContext {
    private static EquipmentReplacementController equipmentReplacementController;
    private static Graph<Integer> lastRequestedGraph;
    private static EquipmentInformation lastProvidedEquipmentInformation;

    public static void run() {
        equipmentReplacementController = new EquipmentReplacementController();
        var frame = new ClientFrame();
        frame.setVisible(true);
    }

    public static Graph<Integer> getEquipmentGraph(EquipmentInformation equipmentInformation) {
        if (!equipmentInformation.equals(lastProvidedEquipmentInformation)) {
            lastRequestedGraph = equipmentReplacementController.getEquipmentGraph(equipmentInformation);
            lastProvidedEquipmentInformation = equipmentInformation;
        }
        return lastRequestedGraph;
    }

    public static EquipmentOptimum getEquipmentOptimumSolution(EquipmentInformation equipmentInformation) {
        EquipmentOptimum optimum;
        if (equipmentInformation.equals(lastProvidedEquipmentInformation)) {
            optimum = equipmentReplacementController.getEquipmentOptimumSolution(equipmentInformation, lastRequestedGraph);
        } else {
            var response = equipmentReplacementController.getEquipmentOptimumSolution(equipmentInformation);
            lastRequestedGraph = response.getRight();
            optimum = response.getLeft();
        }
        lastProvidedEquipmentInformation = equipmentInformation;
        return optimum;
    }
}
