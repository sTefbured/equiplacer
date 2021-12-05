package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input;

import javax.swing.*;
import java.awt.*;

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
        equipmentCostsButton = new JButton(EQUIPMENT_COSTS_BUTTON_STR);
        maintenanceCostsButton = new JButton(MAINTENANCE_COSTS_BUTTON_STR);
        residualValuesButton = new JButton(RESIDUAL_VALUES_BUTTON_STR);
        incomeButton = new JButton(INCOME_BUTTON_STR);
        equipmentCostsButton.setPreferredSize(new Dimension(145, 30));
        maintenanceCostsButton.setPreferredSize(new Dimension(145, 30));
        residualValuesButton.setPreferredSize(new Dimension(145, 30));
        incomeButton.setPreferredSize(new Dimension(145, 30));
    }

    private void addComponents() {
        var textFieldsPanel = new JPanel(new GridBagLayout());
        var constraints = new GridBagConstraints();
        addLabeledTextField(textFieldsPanel, constraints, MAX_EQUIPMENT_AGE_TEXT_FIELD_STR, maxEquipmentAgeTextField);
        addLabeledTextField(textFieldsPanel, constraints, YEARS_COUNT_TEXT_FIELD_STR, yearsCountTextField);
        addLabeledTextField(textFieldsPanel, constraints, CURRENT_EQUIPMENT_AGE_TEXT_FIELD_STR, currentEquipmentAgeTextField);
        add(textFieldsPanel);

        var buttonsPanel = new JPanel(new GridLayout(2, 2, 100, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 10, 40));
        buttonsPanel.add(equipmentCostsButton);
        buttonsPanel.add(maintenanceCostsButton);
        buttonsPanel.add(residualValuesButton);
        buttonsPanel.add(incomeButton);
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
