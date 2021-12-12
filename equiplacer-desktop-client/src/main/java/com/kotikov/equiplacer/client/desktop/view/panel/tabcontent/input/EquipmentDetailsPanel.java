package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input;

import com.kotikov.equiplacer.client.desktop.view.dialog.EquipmentCostsDialog;
import com.kotikov.equiplacer.client.desktop.view.dialog.IncomeDialog;
import com.kotikov.equiplacer.client.desktop.view.dialog.MaintenanceCostsDialog;
import com.kotikov.equiplacer.client.desktop.view.dialog.ResidualValuesDialog;
import com.kotikov.equiplacer.core.model.EquipmentInformation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.GridBagConstraints.*;

public class EquipmentDetailsPanel extends JPanel {
    private static final String BORDER_TITLE = "Equipment details";

    private static final String MAX_EQUIPMENT_AGE_TEXT_FIELD_STR = "Max age";
    private static final String YEARS_COUNT_TEXT_FIELD_STR = "Years count";
    private static final String CURRENT_EQUIPMENT_AGE_TEXT_FIELD_STR = "Current age";

    private static final String EQUIPMENT_COSTS_BUTTON_STR = "Equipment costs";
    private static final String MAINTENANCE_COSTS_BUTTON_STR = "Maintenance costs";
    private static final String RESIDUAL_VALUES_BUTTON_STR = "Residual values";
    private static final String INCOME_BUTTON_STR = "Income";

    private static final Font TEXT_FIELD_FONT = new Font(Font.SERIF, Font.PLAIN, 14);

    private JTextField currentEquipmentAgeTextField;
    private JTextField yearsCountTextField;
    private JTextField maxEquipmentAgeTextField;
    private JButton equipmentCostsButton;
    private JButton maintenanceCostsButton;
    private JButton residualValuesButton;
    private JButton incomeButton;

    private JRadioButton sellRadioButton;
    private JRadioButton notSellRadioButton;

    private List<Integer> equipmentCosts;
    private List<Integer> maintenanceCosts;
    private List<Integer> residualValues;
    private List<Integer> incomes;

    public EquipmentDetailsPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(BORDER_TITLE));
        initializeComponents();
        addComponents();
    }

    private void initializeComponents() {
        initializeTextFields();
        initializeButtons();
    }

    private void initializeTextFields() {
        currentEquipmentAgeTextField = new JTextField();
        yearsCountTextField = new JTextField();
        maxEquipmentAgeTextField = new JTextField();
        currentEquipmentAgeTextField.setFont(TEXT_FIELD_FONT);
        yearsCountTextField.setFont(TEXT_FIELD_FONT);
        maxEquipmentAgeTextField.setFont(TEXT_FIELD_FONT);
    }

    private void initializeButtons() {
        initializeEquipmentCostsButton();
        initializeMaintenanceCostsButton();
        initializeResidualValuesButton();
        initializeIncomeButton();
        initializeRadioButtons();
    }

    //TODO: MAX NEW EQUIPMENT AGE
    public EquipmentInformation getEquipmentInformation() {
        return EquipmentInformation.builder()
                .setMaxAge(Integer.parseInt(maxEquipmentAgeTextField.getText()))
                .setYearsCount(Integer.parseInt(yearsCountTextField.getText()))
                .setCurrentAge(Integer.parseInt(currentEquipmentAgeTextField.getText()))
                .setEquipmentCosts(equipmentCosts)
                .setIncomes(incomes)
                .setResidualCosts(residualValues)
                .setMaintenanceCosts(maintenanceCosts)
                .setMaxNewEquipmentAge(1)
                .build();
    }

    //TODO: IT IS NOT T, IT IS YEAR
    private void initializeEquipmentCostsButton() {
        equipmentCostsButton = new JButton(EQUIPMENT_COSTS_BUTTON_STR);
        equipmentCostsButton.setPreferredSize(new Dimension(145, 30));
        equipmentCostsButton.addActionListener(e -> {
            new EquipmentCostsDialog(this::onEquipmentCostsDialogSubmit,
                    Integer.parseInt(yearsCountTextField.getText())).setVisible(true);
        });
    }

    private void onEquipmentCostsDialogSubmit(List<Integer> equipmentCosts) {
        this.equipmentCosts = equipmentCosts;
    }

    private void initializeMaintenanceCostsButton() {
        maintenanceCostsButton = new JButton(MAINTENANCE_COSTS_BUTTON_STR);
        maintenanceCostsButton.setPreferredSize(new Dimension(145, 30));
        maintenanceCostsButton.addActionListener(e -> {
            new MaintenanceCostsDialog(this::onMaintenanceCostsDialogSubmit,
                    Integer.parseInt(maxEquipmentAgeTextField.getText())).setVisible(true);
        });
    }

    private void onMaintenanceCostsDialogSubmit(List<Integer> maintenanceCosts) {
        this.maintenanceCosts = maintenanceCosts;
    }

    private void initializeResidualValuesButton() {
        residualValuesButton = new JButton(RESIDUAL_VALUES_BUTTON_STR);
        residualValuesButton.setPreferredSize(new Dimension(145, 30));
        residualValuesButton.addActionListener(e -> {
            new ResidualValuesDialog(this::onResidualValuesDialogSubmit,
                    Integer.parseInt(maxEquipmentAgeTextField.getText())).setVisible(true);
        });
    }

    private void onResidualValuesDialogSubmit(List<Integer> residualValues) {
        this.residualValues = residualValues;
    }

    private void initializeIncomeButton() {
        incomeButton = new JButton(INCOME_BUTTON_STR);
        incomeButton.setPreferredSize(new Dimension(145, 30));
        incomeButton.addActionListener(e -> {
            new IncomeDialog(this::onIncomeDialogSubmit,
                    Integer.parseInt(maxEquipmentAgeTextField.getText())).setVisible(true);
        });
    }

    private void onIncomeDialogSubmit(List<Integer> incomes) {
        this.incomes = incomes;
    }

    private void initializeRadioButtons() {
        sellRadioButton = new JRadioButton("Sell last year equipment", true);
        notSellRadioButton = new JRadioButton("Do not last year equipment");
        ButtonGroup sellNotSellGroup = new ButtonGroup();
        sellNotSellGroup.add(sellRadioButton);
        sellNotSellGroup.add(notSellRadioButton);
    }

    private void addComponents() {
        var textFieldsPanel = new JPanel(new GridBagLayout());
        var constraints = new GridBagConstraints();
        addLabeledTextField(textFieldsPanel, constraints, MAX_EQUIPMENT_AGE_TEXT_FIELD_STR, maxEquipmentAgeTextField);
        addLabeledTextField(textFieldsPanel, constraints, YEARS_COUNT_TEXT_FIELD_STR, yearsCountTextField);
        addLabeledTextField(textFieldsPanel, constraints, CURRENT_EQUIPMENT_AGE_TEXT_FIELD_STR, currentEquipmentAgeTextField);
        add(textFieldsPanel);

        var buttonsPanel = new JPanel(new GridLayout(3, 2, 40, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 10, 40));
        buttonsPanel.add(equipmentCostsButton);
        buttonsPanel.add(maintenanceCostsButton);
        buttonsPanel.add(residualValuesButton);
        buttonsPanel.add(incomeButton);
        buttonsPanel.add(sellRadioButton);
        buttonsPanel.add(notSellRadioButton);
        add(buttonsPanel);
    }

    private void addLabeledTextField(JPanel panel, GridBagConstraints constraints, String labelText, JTextField textField) {
        constraints.insets.set(0, 10, 10, 0);
        constraints.gridx = RELATIVE;
        constraints.gridy = 0;
        constraints.anchor = WEST;
        constraints.weightx = 0.05;
        var label = new JLabel(labelText);
        panel.add(label, constraints);

        constraints.insets.set(0, 0, 10, 10);
        constraints.gridx = RELATIVE;
        constraints.fill = HORIZONTAL;
        constraints.weightx = 0.28;
        panel.add(textField, constraints);
    }
}
