package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent;

import com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input.InputPanel;

import javax.swing.*;
import java.awt.*;

public class TabContentPanel extends JPanel {
    public TabContentPanel(JTabbedPane parentPane) {
        super(new GridLayout(1, 2));
        add(new InputPanel(parentPane));

        var placeholder = new JLabel("Placeholder");
        placeholder.setHorizontalAlignment(SwingConstants.CENTER);
        add(placeholder);
    }
}
