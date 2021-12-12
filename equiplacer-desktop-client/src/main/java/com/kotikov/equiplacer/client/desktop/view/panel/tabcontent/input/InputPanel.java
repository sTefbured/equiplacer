package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input;

import com.kotikov.equiplacer.client.desktop.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    private static final String[] TABLE_HEADERS = new String[]{
            "Age", "Income r(t)", "Maintenance cost c(t)", "Residual value s(t)"
    };

    private final JButton calculateButton;
    private final JTable costsTable;
    private final EquipmentDetailsPanel equipmentDetailsPanel;

    public InputPanel(JTabbedPane parentTabbedPane) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        equipmentDetailsPanel = new EquipmentDetailsPanel();
        add(equipmentDetailsPanel);
        add(Box.createRigidArea(new Dimension(0, 5)));
        costsTable = new JTable(new Object[][]{{"1", "2", "3", "4"}}, TABLE_HEADERS); //TODO: delete when table logics are implemented
        add(new JScrollPane(costsTable));
        calculateButton = new JButton("Calculate");
        addCalculateButtonListener();
        var calculateButtonWrapper = new JPanel();
        calculateButtonWrapper.add(calculateButton);
        add(calculateButtonWrapper);
    }

    private void addCalculateButtonListener() {
        calculateButton.addActionListener(e -> {
            var equipmentInformation = equipmentDetailsPanel.getEquipmentInformation();
            var solution = ApplicationContext.getEquipmentOptimumSolution(equipmentInformation);
            for (int i = 0; i < 4; i++) {
                switch (solution.getReplacementDecision()) {
                    case REPLACE -> System.out.println("R");
                    case KEEP -> System.out.println("K");
                    case BOTH -> System.out.println("B");
                }
                try {
                    solution = solution.getNextOptimums().get(0);
                } catch (Exception ex) {
                    System.out.println("EXCEPTION LOL");
                }
            }
        });
    }
}
