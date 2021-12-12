package com.kotikov.equiplacer.client.desktop.view.dialog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IncomeDialog extends JDialog {
    private List<JTextField> incomeTextFields;

    public IncomeDialog(Consumer<List<Integer>> onSubmitClick, int maxAge) {
        super((JDialog)null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel fieldsPanel = new JPanel(new GridLayout(maxAge + 2, 1, 5, 5));
        fieldsPanel.add(new JLabel("Age"));
        fieldsPanel.add(new JLabel("Income"));
        incomeTextFields = new ArrayList<>(maxAge);
        for (int i = 0; i < maxAge + 1; i++) {
            fieldsPanel.add(new JLabel(String.valueOf(i)));
            incomeTextFields.add(new JTextField());
            fieldsPanel.add(incomeTextFields.get(i));
        }
        add(fieldsPanel);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            onSubmitClick.accept(getResidualValues());
            setVisible(false);
            dispose();
        });
        add(submitButton);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private List<Integer> getResidualValues() {
        return incomeTextFields.stream()
                .map(textField -> Integer.parseInt(textField.getText()))
                .toList();
    }
}
