package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input;

import javax.swing.*;

public class InputPanel extends JPanel {
    private static final String[] TABLE_HEADERS = new String[]{
            "Age", "Income r(t)", "Maintenance cost c(t)", "Residual value s(t)"
    };

    private final JButton calculateButton = new JButton("Calculate") {
        {
            addActionListener(e -> {

            });
        }
    };

    private final JTable costsTable;

    public InputPanel(JTabbedPane parentTabbedPane) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new EquipmentDetailsPanel());

        costsTable = new JTable(new Object[][]{{"1", "2", "3", "4"}}, TABLE_HEADERS);
        add(new JScrollPane(costsTable));

        var calculateButtonWrapper = new JPanel();
        calculateButtonWrapper.add(calculateButton);
        add(calculateButtonWrapper);
    }
}
