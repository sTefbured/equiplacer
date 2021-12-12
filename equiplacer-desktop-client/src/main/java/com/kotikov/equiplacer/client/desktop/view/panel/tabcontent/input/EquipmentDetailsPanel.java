package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input;

import com.kotikov.equiplacer.client.desktop.view.dialog.CostsInputDialog;
import com.kotikov.equiplacer.core.model.EquipmentInformation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EquipmentDetailsPanel extends JPanel {
    private static final String BORDER_TITLE = "Equipment details";

    private static final String MAX_EQUIPMENT_AGE_TEXT_FIELD_STR = "Max age";
    private static final String MAX_NEW_EQUIPMENT_AGE_TEXT_FIELD_STR = "Max new equipment age";
    private static final String YEARS_COUNT_TEXT_FIELD_STR = "Years count";
    private static final String CURRENT_EQUIPMENT_AGE_TEXT_FIELD_STR = "Current age";

    private static final String COSTS_INPUT_BUTTON_STR = "Enter costs";

    private static final Font TEXT_FIELD_FONT = new Font(Font.SERIF, Font.PLAIN, 14);

    private JTextField currentEquipmentAgeTextField;
    private JTextField yearsCountTextField;
    private JTextField maxEquipmentAgeTextField;
    private JTextField maxNewEquipmentAgeTextField;
    private JButton enterCostsButton;

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
        initializeEnterCostsButton();
    }

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

    private void initializeEnterCostsButton() {
        enterCostsButton = new JButton(COSTS_INPUT_BUTTON_STR);
        enterCostsButton.setPreferredSize(new Dimension(145, 30));
        enterCostsButton.addActionListener(e -> new CostsInputDialog(this::onCostsInputDialogSubmit,
                Integer.parseInt(maxEquipmentAgeTextField.getText())).setVisible(true));
    }

    private void onCostsInputDialogSubmit(List<Integer> incomes, List<Integer> maintenanceCosts, List<Integer> residualValues) {
        this.incomes = incomes;
        this.maintenanceCosts = maintenanceCosts;
        this.residualValues = residualValues;
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

        var enterCostsButtonWrapper = new JPanel();
        enterCostsButtonWrapper.add(enterCostsButton);
        add(enterCostsButtonWrapper);
    }

    private void addLabeledTextField(JPanel panel, String labelText, JTextField textField) {
        var wrapper = new JPanel(new GridLayout(1, 2, 30, 0));
        wrapper.add(new JLabel(labelText));
        wrapper.add(textField);
        panel.add(wrapper);
    }
}
