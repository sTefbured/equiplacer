package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input;

import com.kotikov.equiplacer.client.desktop.context.ApplicationContext;
import com.kotikov.equiplacer.client.desktop.view.dialog.OutputDialog;
import com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.TabContentPanel;
import com.kotikov.equiplacer.core.model.EquipmentInformation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class InputPanel extends JPanel {
    private static final String[] TABLE_HEADERS = new String[]{
            "Age", "Income r(t)", "Maintenance cost c(t)", "Residual value s(t)"
    };

    private final TabContentPanel parentTabContentPanel;

    private final JButton calculateButton;
    private final JButton drawGraphButton;
    private final EquipmentDetailsPanel equipmentDetailsPanel;

    private JDialog missingInfoDialog;
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
        drawGraphButton = new JButton("Draw graph");
        addDrawGraphButtonListener();
        var buttonWrapper = new JPanel(new GridLayout(1, 2, 200, 0));
        buttonWrapper.add(drawGraphButton);
        buttonWrapper.add(calculateButton);
        add(buttonWrapper);
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
//            var equipmentInformation = equipmentDetailsPanel.getEquipmentInformation();
//            if (equipmentInformation == null) {
//                showMissingInfoDialog();
//                return;
//            }
            var equipmentInformation = EquipmentInformation.builder()
                    .setMaxNewEquipmentAge(1)
                    .setIncomes(List.of(20000, 19000, 18500, 17200, 15500, 14000, 12200))
                    .setResidualCosts(List.of(100000, 80000, 60000, 50000, 30000, 10000, 5000))
                    .setMaintenanceCosts(List.of(200, 600, 1200, 1500, 1700, 1800, 2200))
                    .setCurrentAge(3)
                    .setMaxAge(6)
                    .setSellLastYearEquipmentOn(true)
                    .setYearsCount(4)
                    .build();
            var solution = ApplicationContext.getEquipmentOptimumSolution(equipmentInformation);
            parentTabContentPanel.initializeGraph(ApplicationContext.getEquipmentGraph(equipmentInformation));
            new OutputDialog(solution, parentTabContentPanel).setVisible(true);
        });
    }

    private void showMissingInfoDialog() {
        if (missingInfoDialog != null) {
            return;
        }
        missingInfoDialog = new JDialog(ApplicationContext.getClientFrame());
        var label = new JLabel("Please provide full information about equipment");
        label.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        missingInfoDialog.add(label);
        missingInfoDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                missingInfoDialog = null;
            }
        });
        missingInfoDialog.pack();
        missingInfoDialog.setResizable(false);
        missingInfoDialog.setLocationRelativeTo(ApplicationContext.getClientFrame());
        missingInfoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        missingInfoDialog.setVisible(true);
    }

    private void addDrawGraphButtonListener() {
        drawGraphButton.addActionListener(e -> {
//            var equipmentInformation = equipmentDetailsPanel.getEquipmentInformation();
//            if (equipmentInformation == null) {
//                showMissingInfoDialog();
//                return;
//            }
            var equipmentInformation = EquipmentInformation.builder()
                                .setMaxNewEquipmentAge(1)
                                .setIncomes(List.of(20000, 19000, 18500, 17200, 15500, 14000, 12200))
                                .setResidualCosts(List.of(100000, 80000, 60000, 50000, 30000, 10000, 5000))
                                .setMaintenanceCosts(List.of(200, 600, 1200, 1500, 1700, 1800, 2200))
                                .setCurrentAge(3)
                                .setMaxAge(6)
                                .setSellLastYearEquipmentOn(true)
                                .setYearsCount(4)
                                .build();
            parentTabContentPanel.initializeGraph(ApplicationContext.getEquipmentGraph(equipmentInformation));
        });
    }
}
