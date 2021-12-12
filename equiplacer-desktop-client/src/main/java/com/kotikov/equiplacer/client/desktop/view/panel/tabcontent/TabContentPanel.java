package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent;

import com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input.InputPanel;
import com.kotikov.equiplacer.jgraphpanel.JGraphPanel;

import javax.swing.*;
import java.awt.*;

public class TabContentPanel extends JPanel {
    private static final String X_AXIS_TITLE = "Year";
    private static final String Y_AXIS_TITLE = "Age";

    public TabContentPanel(JTabbedPane parentPane) {
        super(new GridLayout(1, 2));
        add(new InputPanel(parentPane));
        add(new JGraphPanel<Integer>(X_AXIS_TITLE, Y_AXIS_TITLE));
    }
}
