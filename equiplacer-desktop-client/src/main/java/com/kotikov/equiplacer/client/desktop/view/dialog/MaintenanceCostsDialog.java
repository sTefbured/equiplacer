package com.kotikov.equiplacer.client.desktop.view.dialog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MaintenanceCostsDialog extends JDialog {
    private List<JTextField> maintenanceTextFields;

    public MaintenanceCostsDialog(Consumer<List<Integer>> onSubmitClick, int maxAge) {
        super((JDialog)null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel fieldsPanel = new JPanel(new GridLayout(maxAge + 2, 1, 5, 5));
        fieldsPanel.add(new JLabel("Age"));
        fieldsPanel.add(new JLabel("Maintenance cost"));
        maintenanceTextFields = new ArrayList<>(maxAge);
        for (int i = 0; i < maxAge + 1; i++) {
            fieldsPanel.add(new JLabel(String.valueOf(i)));
            maintenanceTextFields.add(new JTextField());
            fieldsPanel.add(maintenanceTextFields.get(i));
        }
        add(fieldsPanel);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            onSubmitClick.accept(getMaintenanceCosts());
            setVisible(false);
            dispose();
        });
        add(submitButton);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private List<Integer> getMaintenanceCosts() {
        return maintenanceTextFields.stream()
                .map(textField -> Integer.parseInt(textField.getText()))
                .toList();
    }
}
