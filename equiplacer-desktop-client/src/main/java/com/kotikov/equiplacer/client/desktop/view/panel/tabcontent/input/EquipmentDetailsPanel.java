package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input;

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
    private static final String MAX_NEW_EQUIPMENT_AGE_TEXT_FIELD_STR = "Max new equipment age";
    private static final String YEARS_COUNT_TEXT_FIELD_STR = "Years count";
    private static final String CURRENT_EQUIPMENT_AGE_TEXT_FIELD_STR = "Current age";

    private static final String MAINTENANCE_COSTS_BUTTON_STR = "Maintenance costs";
    private static final String RESIDUAL_VALUES_BUTTON_STR = "Residual values";
    private static final String INCOME_BUTTON_STR = "Income";

    private static final Font TEXT_FIELD_FONT = new Font(Font.SERIF, Font.PLAIN, 14);

    private JTextField currentEquipmentAgeTextField;
    private JTextField yearsCountTextField;
    private JTextField maxEquipmentAgeTextField;
    private JTextField maxNewEquipmentAgeTextField;
    private JButton maintenanceCostsButton;
    private JButton residualValuesButton;
    private JButton incomeButton;

    private JRadioButton sellRadioButton;
    private JRadioButton notSellRadioButton;

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
        maxNewEquipmentAgeTextField = new JTextField();
        currentEquipmentAgeTextField.setFont(TEXT_FIELD_FONT);
        yearsCountTextField.setFont(TEXT_FIELD_FONT);
        maxEquipmentAgeTextField.setFont(TEXT_FIELD_FONT);
        maxNewEquipmentAgeTextField.setFont(TEXT_FIELD_FONT);
    }

    private void initializeButtons() {
        initializeRadioButtons();
        initializeMaintenanceCostsButton();
        initializeResidualValuesButton();
        initializeIncomeButton();
    }

    //TODO: MAX NEW EQUIPMENT AGE
    public EquipmentInformation getEquipmentInformation() {
        return EquipmentInformation.builder()
                .setMaxAge(Integer.parseInt(maxEquipmentAgeTextField.getText()))
                .setYearsCount(Integer.parseInt(yearsCountTextField.getText()))
                .setCurrentAge(Integer.parseInt(currentEquipmentAgeTextField.getText()))
                .setMaxNewEquipmentAge(Integer.parseInt(maxNewEquipmentAgeTextField.getText()))
                .setIncomes(incomes)
                .setResidualCosts(residualValues)
                .setMaintenanceCosts(maintenanceCosts)
                .setSellLastYearEquipmentOn(sellRadioButton.isSelected())
                .build();
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
        notSellRadioButton = new JRadioButton("Do not sell last year equipment");
        ButtonGroup sellNotSellGroup = new ButtonGroup();
        sellNotSellGroup.add(sellRadioButton);
        sellNotSellGroup.add(notSellRadioButton);
    }

    private void addComponents() {
        var textFieldsPanel = new JPanel(new GridLayout(2, 2, 40, 10));
        addLabeledTextField(textFieldsPanel, MAX_EQUIPMENT_AGE_TEXT_FIELD_STR, maxEquipmentAgeTextField);
        addLabeledTextField(textFieldsPanel, YEARS_COUNT_TEXT_FIELD_STR, yearsCountTextField);
        addLabeledTextField(textFieldsPanel, CURRENT_EQUIPMENT_AGE_TEXT_FIELD_STR, currentEquipmentAgeTextField);
        addLabeledTextField(textFieldsPanel, MAX_NEW_EQUIPMENT_AGE_TEXT_FIELD_STR, maxNewEquipmentAgeTextField);
        add(textFieldsPanel);

        var radioButtonsPanel = new JPanel(new GridLayout(1, 2));
        radioButtonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        sellRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
        notSellRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
        radioButtonsPanel.add(sellRadioButton);
        radioButtonsPanel.add(notSellRadioButton);
        add(radioButtonsPanel);

        var buttonsPanel = new JPanel(new GridLayout(1, 2, 40, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 0, 40));
        buttonsPanel.add(maintenanceCostsButton);
        buttonsPanel.add(residualValuesButton);
        buttonsPanel.add(incomeButton);
        add(buttonsPanel);}

    private void addLabeledTextField(JPanel panel, String labelText, JTextField textField) {
        var wrapper = new JPanel(new GridLayout(1, 2, 30, 0));
        wrapper.add(new JLabel(labelText));
        wrapper.add(textField);
        panel.add(wrapper);
    }
}
