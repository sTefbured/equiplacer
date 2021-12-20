package com.kotikov.equiplacer.client.desktop.view.panel.tabcontent.input.util;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IntegerTextField extends JTextField {
    public IntegerTextField() {
        super();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
    }
}
