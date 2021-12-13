package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input;

import com.kotikov.equiplacer.client.desktop.context.ApplicationContext;
import com.kotikov.equiplacer.client.desktop.util.SolutionSequenceToStringListConverter;
import com.kotikov.equiplacer.client.desktop.view.dialog.CostsInputDialog;
import com.kotikov.equiplacer.client.desktop.view.dialog.OutputDialog;
import com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.TabContentPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InputPanel extends JPanel {
    private static final String[] TABLE_HEADERS = new String[]{
            "Age", "Income r(t)", "Maintenance cost c(t)", "Residual value s(t)"
    };

    private final TabContentPanel parentTabContentPanel;

    private final JButton calculateButton;
    private final EquipmentDetailsPanel equipmentDetailsPanel;

    private JTable costsTable;

    public InputPanel(TabContentPanel parentTabContentPanel) {
        this.parentTabContentPanel = parentTabContentPanel;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        equipmentDetailsPanel = new EquipmentDetailsPanel(this);
        add(equipmentDetailsPanel);
        add(Box.createRigidArea(new Dimension(0, 5)));
        initializeCostsTable();
        add(new JScrollPane(costsTable));
        calculateButton = new JButton("Calculate");
        addCalculateButtonListener();
        var calculateButtonWrapper = new JPanel();
        calculateButtonWrapper.add(calculateButton);
        add(calculateButtonWrapper);
    }

    private void initializeCostsTable() {
        var tableModel = new DefaultTableModel(TABLE_HEADERS, 0);
        costsTable = new JTable(tableModel);
    }

    public void setTableValues(List<Integer> incomes, List<Integer> maintenanceCosts, List<Integer> residualValues) {
        var tableModel = new DefaultTableModel(TABLE_HEADERS, incomes.size()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < incomes.size(); i++) {
            tableModel.setValueAt(String.valueOf(i), i, 0);
            tableModel.setValueAt(String.valueOf(incomes.get(i)), i, 1);
            tableModel.setValueAt(String.valueOf(maintenanceCosts.get(i)), i, 2);
            tableModel.setValueAt(String.valueOf(residualValues.get(i)), i, 3);
        }
        costsTable.setModel(tableModel);
    }

    private void addCalculateButtonListener() {
        calculateButton.addActionListener(e -> {
            var equipmentInformation = equipmentDetailsPanel.getEquipmentInformation();
            var solution = ApplicationContext.getEquipmentOptimumSolution(equipmentInformation);
            parentTabContentPanel.initializeGraph(ApplicationContext.getEquipmentGraph(equipmentInformation));
            new OutputDialog(SolutionSequenceToStringListConverter.convert(solution)).setVisible(true);
        });
    }
}
