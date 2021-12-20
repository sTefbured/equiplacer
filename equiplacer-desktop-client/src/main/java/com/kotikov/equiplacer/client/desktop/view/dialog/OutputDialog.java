package com.kotikov.equiplacer.client.desktop.view.dialog;

import com.kotikov.equiplacer.client.desktop.context.ApplicationContext;
import com.kotikov.equiplacer.client.desktop.util.SolutionSequenceToStringListConverter;
import com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.TabContentPanel;
import com.kotikov.equiplacer.core.model.EquipmentOptimum;

import javax.swing.*;

public class OutputDialog extends JDialog {
    private final JList<String> solutionsList;

    public OutputDialog(EquipmentOptimum solutions, TabContentPanel tabContentPanel) {
        super(ApplicationContext.getClientFrame());
        var strSolutions = SolutionSequenceToStringListConverter.convert(solutions);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        var listModel = new DefaultListModel<String>();
        for (int i = 0; i < strSolutions.size(); i++) {
            listModel.add(i, strSolutions.get(i));
        }
        solutionsList = new JList<>(listModel);
        addSolutionsListListener(tabContentPanel);
        add(solutionsList);
        pack();
        setLocationRelativeTo(ApplicationContext.getClientFrame());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void addSolutionsListListener(TabContentPanel tabContentPanel) {
        solutionsList.addListSelectionListener(e -> {
            var graphPanel = tabContentPanel.getGraphPanel();
            var firstIndex = e.getFirstIndex();
            var item = solutionsList.getModel().getElementAt(firstIndex);
//            for (int currentIndex = 0; currentIndex < item.length(); ) {
//
//            }
            graphPanel.removeLastAddedGraph();
            graphPanel.addGraph(null);
        });
    }
}
