package com.kotikov.equiplacer.client.desktop.view.dialog;

import javax.swing.*;
import java.util.List;

public class OutputDialog extends JDialog {
    public OutputDialog(List<String> solutions) {
        super((JDialog)null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        var listModel = new DefaultListModel<String>();
        for (int i = 0; i < solutions.size(); i++) {
            listModel.add(i, solutions.get(i));
        }
        var solutionsList = new JList<>(listModel);
        add(solutionsList);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
