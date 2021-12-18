package com.kotikov.equiplacer.client.desktop.view.panel.tabbedpane;

import com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.TabContentPanel;

import javax.swing.*;
import java.awt.*;

public class TabbedPaneWrapper extends JPanel {
    private JTabbedPane tabbedPane;

    public TabbedPaneWrapper() {
        setLayout(new BorderLayout());
        initializeTabbedPane();
        add(tabbedPane);
        tabbedPane.setUI(new CustomTabbedPaneUI());
    }

    private void initializeTabbedPane() {
        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        addNewTab();
        addNewTab();
    }

    private void addNewTab() {
        var tabComponent = new JPanel();
        tabComponent.setLayout(new BoxLayout(tabComponent, BoxLayout.X_AXIS));
        tabComponent.setOpaque(false);

        var tabIndex = tabbedPane.getTabCount();
        var title = "Tab " + (tabIndex + 1);
        tabComponent.add(new JLabel(title));

        var contentPanel = new TabContentPanel(tabbedPane);
        tabbedPane.addTab(null, contentPanel);

        var closeButtonWrapper = new JPanel();
        closeButtonWrapper.setOpaque(false);
        closeButtonWrapper.add(new TabCloseButton(tabbedPane, tabComponent));
        tabComponent.add(closeButtonWrapper);
        tabbedPane.setTabComponentAt(tabIndex, tabComponent);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }
}
