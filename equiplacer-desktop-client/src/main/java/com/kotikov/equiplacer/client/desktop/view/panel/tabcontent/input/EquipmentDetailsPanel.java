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

    private static final Font TEXT_FIELD_FONT = new Font(Font.SERIF, Font.PLAIN, 14);

    private final GridBagConstraints layoutConstraints;
    private final Insets insets = new Insets(0, 0, 0, 0);

    private JTextField currentEquipmentAgeTextField;
    private JTextField yearsCountTextField;
    private JTextField maxEquipmentAgeTextField;
    private JButton equipmentCostsButton;
    private JButton maintenanceCostsButton;
    private JButton residualValuesButton;

    public EquipmentDetailsPanel() {
        super(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(BORDER_TITLE));
        layoutConstraints = new GridBagConstraints();
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
        equipmentCostsButton.setPreferredSize(new Dimension(145, 30));
        maintenanceCostsButton.setPreferredSize(new Dimension(145, 30));
        residualValuesButton.setPreferredSize(new Dimension(145, 30));
    }

    private void addComponents() {
        addLabeledTextField(MAX_EQUIPMENT_AGE_TEXT_FIELD_STR, maxEquipmentAgeTextField);
        addLabeledTextField(YEARS_COUNT_TEXT_FIELD_STR, yearsCountTextField);
        addLabeledTextField(CURRENT_EQUIPMENT_AGE_TEXT_FIELD_STR, currentEquipmentAgeTextField);
        addButton(equipmentCostsButton, 0);
        addButton(maintenanceCostsButton, 2);
        addButton(residualValuesButton, 4);
    }

    private void addLabeledTextField(String labelText, JTextField textField) {
        setDefaultLayoutConstraints();
        insets.set(0, 5, 10, 10);
        layoutConstraints.gridx = RELATIVE;
        layoutConstraints.gridy = 0;
        layoutConstraints.anchor = WEST;
        var label = new JLabel(labelText);
        add(label, layoutConstraints);

        insets.set(0, 0, 10, 5);
        layoutConstraints.gridx = RELATIVE;
        layoutConstraints.fill = HORIZONTAL;
        add(textField, layoutConstraints);
    }

    private void addButton(JButton button, int x) {
        setDefaultLayoutConstraints();
        layoutConstraints.gridx = x;
        layoutConstraints.gridy = RELATIVE;
        layoutConstraints.gridwidth = 2;
        insets.set(0, 0, 10, 0);
        layoutConstraints.weightx = 0.333333;
        add(button, layoutConstraints);
    }

    private void setDefaultLayoutConstraints() {
        layoutConstraints.gridx = RELATIVE;
        layoutConstraints.gridy = RELATIVE;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridheight = 1;

        layoutConstraints.weightx = 0;
        layoutConstraints.weighty = 0;
        layoutConstraints.anchor = CENTER;
        layoutConstraints.fill = NONE;

        insets.set(0, 0, 0, 0);
        layoutConstraints.insets = insets;
        layoutConstraints.ipadx = 0;
        layoutConstraints.ipady = 0;
    }
}
