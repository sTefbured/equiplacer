package com.kotikov.equiplacer.client.desktop.view.panel.tabbedpane;


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
        //TODO: change to a panel with input components
        JPanel panel = new JPanel();
        panel.add(new JLabel("TRUE"));

        var tabIndex = tabbedPane.getTabCount();
        var title = "Tab " + (tabIndex + 1);
        if (tabIndex == 1) {
            title = "dfdgsggsdgdsfgdfgdsgdfsgfds 2";
        }
        tabbedPane.addTab(null, new JPanel());
        var tabComponent = new JPanel();
        tabComponent.setLayout(new BoxLayout(tabComponent, BoxLayout.X_AXIS));
        tabComponent.setOpaque(false);
        tabComponent.add(new JLabel(title));
        var closeButtonWrapper = new JPanel();
        closeButtonWrapper.setOpaque(false);
        closeButtonWrapper.add(new TabCloseButton(tabbedPane, tabComponent));
        tabComponent.add(closeButtonWrapper);
        tabbedPane.setTabComponentAt(tabIndex, tabComponent);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }
}
