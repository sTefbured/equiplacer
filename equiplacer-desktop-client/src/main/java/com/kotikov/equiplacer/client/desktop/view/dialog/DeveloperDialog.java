package com.kotikov.equiplacer.client.desktop.view.dialog;

import com.kotikov.equiplacer.client.desktop.context.ApplicationContext;
import com.kotikov.equiplacer.client.desktop.model.SolutionStage;
import com.kotikov.equiplacer.client.desktop.model.SolutionStageRow;
import com.kotikov.equiplacer.core.model.EquipmentInformation;
import com.kotikov.equiplacer.graph.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class DeveloperDialog extends JDialog {
    private final List<SolutionStage> solutionStages;
    private final JTable stageTable;

    private JLabel stageLabel;
    private JButton loadLastButton;
    private JButton nextButton;
    private JButton backButton;
    private int currentStage;
    private int maxNewEquipmentAge;

    public DeveloperDialog() {
        super(ApplicationContext.getClientFrame());
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        solutionStages = new ArrayList<>();
        currentStage = 0;
        stageTable = new JTable();
        initializeStageLabel();
        initializeLoadLastButton();
        initializeNextButton();
        initializeBackButton();
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(loadLastButton);
        var pagePanel = new JPanel();
        pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.Y_AXIS));
        pagePanel.add(stageLabel);
        var scrollPane = new JScrollPane(stageTable);
        pagePanel.add(scrollPane);
        add(pagePanel);

        var buttonsPanel = new JPanel(new GridLayout(1, 2, 300, 0));
        buttonsPanel.add(backButton);
        buttonsPanel.add(nextButton);
        add(buttonsPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(ApplicationContext.getClientFrame());
    }

    private void initializeStageLabel() {
        stageLabel = new JLabel("No data loaded");
        stageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        stageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }

    private void setTableValues(SolutionStage stage) {
        var tableModel = getTableModelWithHeaders(stage);
        var rows = stage.getRows();
        for (int i = 0; i < rows.size(); i++) {
            var currentRow = rows.get(i);
            var columnIndex = 0;
            tableModel.setValueAt(currentRow.getAge(), i, columnIndex);
            tableModel.setValueAt(currentRow.getSaveFunctionValue(), i, ++columnIndex);
            var replaceFunctionValues = currentRow.getReplaceFunctionValues();
            for (Double replaceFunctionValue : replaceFunctionValues) {
                tableModel.setValueAt(replaceFunctionValue, i, ++columnIndex);
            }
            tableModel.setValueAt(currentRow.getMaxFunctionValue(), i, ++columnIndex);
            var decisions = currentRow.getReplacementDecision().stream().map(Enum::toString).toList();
            var decisionString = String.join(" or ", decisions);
            tableModel.setValueAt(decisionString, i, ++columnIndex);
        }
        stageTable.setModel(tableModel);
    }

    private TableModel getTableModelWithHeaders(SolutionStage stage) {
        var headers = new String[maxNewEquipmentAge + 4];
        var index = 0;
        headers[index++] = "t";
        headers[index++] = "Keep";
        for (int i = 0; i < maxNewEquipmentAge; i++) {
            headers[index++] = "Replace " + (i + 1);
        }
        headers[index++] = "f";
        headers[index] = "Decision";
        return new DefaultTableModel(headers, stage.getRows().size()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void initializeLoadLastButton() {
        loadLastButton = new JButton("Load last solution");
        loadLastButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        loadLastButton.addActionListener(e -> {
            var graph = ApplicationContext.getLastRequestedGraph();
            var ageLayers = new LinkedList<List<Node<Integer>>>();
            var layeredIterator = graph.layeredIterator();
            layeredIterator.forEachRemaining(ageLayers::addFirst);
            ageLayers.pop();
            var equipmentInformation = ApplicationContext.getLastProvidedEquipmentInformation();
            loadSolutions(equipmentInformation, ageLayers);
            maxNewEquipmentAge = equipmentInformation.getMaxNewEquipmentAge();
            setTableValues(solutionStages.get(currentStage));
            stageLabel.setText(String.valueOf(solutionStages.get(currentStage).getYear()));
        });
    }

    private void loadSolutions(EquipmentInformation equipmentInformation, LinkedList<List<Node<Integer>>> layers) {
        SolutionStage lastStage = null;
        for (int i = equipmentInformation.getYearsCount(); i > 0 ; i--) {
            SolutionStage solutionStage = new SolutionStage(i);
            var currentLayer = layers.pop();
            for (var node : currentLayer) {
                var saveIncome = getSaveIncome(node.getData(), i, equipmentInformation.getMaxAge(), equipmentInformation,
                        lastStage);
                var replaceIncomes = new ArrayList<Double>();
                for (int j = 1; j <= equipmentInformation.getMaxNewEquipmentAge(); j++) {
                    replaceIncomes.add(getReplaceIncome(node.getData(), i, j, equipmentInformation, lastStage));
                }
                var solutionStageRow = new SolutionStageRow(node.getData(), saveIncome, replaceIncomes);
                solutionStage.addRow(solutionStageRow);
            }
            lastStage = solutionStage;
            solutionStages.add(solutionStage);
        }
    }

    private double getSaveIncome(int age, int year, int maxAge, EquipmentInformation equipmentInformation,
                                 SolutionStage solutionStage) {
        if (age == maxAge) {
            return Double.NEGATIVE_INFINITY;
        }
        var lastFunctionValue = solutionStage == null ? 0 : solutionStage.getRows().stream()
                .filter(row -> row.getAge() == age + 1)
                .findFirst()
                .orElseThrow()
                .getMaxFunctionValue();
        var result = equipmentInformation.getIncomes().get(age) - equipmentInformation.getMaintenanceCosts().get(age);
        result += lastFunctionValue;
        if (equipmentInformation.isSellLastYearEquipmentOn() && equipmentInformation.getYearsCount() == year) {
            result += equipmentInformation.getResidualCosts().get(age + 1);
        }
        return result;
    }

    private double getReplaceIncome(int age, int year, int newEquipmentAge, EquipmentInformation equipmentInformation,
                                    SolutionStage solutionStage) {
        var lastFunctionValue = solutionStage != null
                ? solutionStage.getRows().stream().filter(row -> row.getAge() == newEquipmentAge).map(SolutionStageRow::getMaxFunctionValue)
                .findFirst().orElse(0.0)
                : 0;
        var result = equipmentInformation.getIncomes().get(newEquipmentAge - 1) + equipmentInformation.getResidualCosts().get(age);
        result += -equipmentInformation.getResidualCosts().get(newEquipmentAge - 1) + lastFunctionValue;
        result -= equipmentInformation.getMaintenanceCosts().get(newEquipmentAge - 1);
        if (equipmentInformation.isSellLastYearEquipmentOn() && equipmentInformation.getYearsCount() == year) {
            result += equipmentInformation.getResidualCosts().get(newEquipmentAge);
        }
        return result;
    }

    private void initializeNextButton() {
        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            currentStage++;
            if (currentStage == solutionStages.size() - 1) {
                nextButton.setEnabled(false);
            }
            if (currentStage > 0) {
                backButton.setEnabled(true);
            }
            setTableValues(solutionStages.get(currentStage));
            stageLabel.setText(String.valueOf(solutionStages.get(currentStage).getYear()));
            revalidate();
            repaint();
        });
    }

    private void initializeBackButton() {
        backButton = new JButton("Back");
        backButton.setEnabled(false);
        backButton.addActionListener(e -> {
            currentStage--;
            if (currentStage == 0) {
                backButton.setEnabled(false);
            }
            if (currentStage < solutionStages.size() - 1) {
                nextButton.setEnabled(true);
            }
            setTableValues(solutionStages.get(currentStage));
            stageLabel.setText(String.valueOf(solutionStages.get(currentStage).getYear()));
            revalidate();
            repaint();
        });
    }
}
