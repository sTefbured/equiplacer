package com.kotikov.equiplacer.client.desktop.view.dialog;

import org.apache.logging.log4j.util.TriConsumer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CostsInputDialog extends JDialog {
    private List<JTextField> incomeTextFields;
    private List<JTextField> maintenanceTextFields;
    private List<JTextField> residualValueTextFields;

    public CostsInputDialog(TriConsumer<List<Integer>, List<Integer>, List<Integer>> onSubmitClick, int maxAge) {
        super((JDialog)null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        addComponents(onSubmitClick, maxAge);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void addComponents(TriConsumer<List<Integer>, List<Integer>, List<Integer>> onSubmitClick, int maxAge) {
        var fieldsPanel = new JPanel(new GridLayout(maxAge + 2, 4, 5, 5));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        fieldsPanel.add(new JLabel("Age", SwingConstants.CENTER));
        fieldsPanel.add(new JLabel("Income", SwingConstants.CENTER));
        fieldsPanel.add(new JLabel("Maintenance cost", SwingConstants.CENTER));
        fieldsPanel.add(new JLabel("Residual value", SwingConstants.CENTER));
        incomeTextFields = new ArrayList<>(maxAge);
        maintenanceTextFields = new ArrayList<>(maxAge);
        residualValueTextFields = new ArrayList<>(maxAge);
        for (int i = 0; i < maxAge + 1; i++) {
            fieldsPanel.add(new JLabel(String.valueOf(i), SwingConstants.CENTER));
            incomeTextFields.add(new JTextField());
            maintenanceTextFields.add(new JTextField());
            residualValueTextFields.add(new JTextField());
            fieldsPanel.add(incomeTextFields.get(i));
            fieldsPanel.add(maintenanceTextFields.get(i));
            fieldsPanel.add(residualValueTextFields.get(i));
        }
        add(fieldsPanel);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            onSubmitClick.accept(getIncomes(), getMaintenanceCosts(), getResidualValues());
            setVisible(false);
            dispose();
        });
        var buttonWrapper = new JPanel();
        buttonWrapper.add(submitButton);
        add(buttonWrapper);
    }

    private List<Integer> getIncomes() {
        return incomeTextFields.stream()
                .map(textField -> Integer.parseInt(textField.getText()))
                .toList();
    }

    private List<Integer> getMaintenanceCosts() {
        return maintenanceTextFields.stream()
                .map(textField -> Integer.parseInt(textField.getText()))
                .toList();
    }

    private List<Integer> getResidualValues() {
        return residualValueTextFields.stream()
                .map(textField -> Integer.parseInt(textField.getText()))
                .toList();
    }
}
