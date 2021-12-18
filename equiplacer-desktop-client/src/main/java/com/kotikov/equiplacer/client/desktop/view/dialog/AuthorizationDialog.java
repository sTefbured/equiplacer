package com.kotikov.equiplacer.client.desktop.view.dialog;

import com.kotikov.equiplacer.client.desktop.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AuthorizationDialog extends JDialog {
    private static final String DIALOG_MESSAGE = "Please enter password to get access to developer frame";
    private static final Font FONT = new Font(Font.DIALOG, Font.BOLD, 16);
    private static final char[] TEST_PASSWORD = "password".toCharArray();

    private JPasswordField passwordTextField;
    private JButton submitButton;

    public AuthorizationDialog() {
        super(ApplicationContext.getClientFrame());
        var panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 0, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        initializePasswordTextField();
        initializeSubmitButton();
        panel.add(new JLabel(DIALOG_MESSAGE));
        panel.add(passwordTextField);
        panel.add(submitButton);
        add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(ApplicationContext.getClientFrame());
    }

    private void initializeSubmitButton() {
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submit());
    }

    private void initializePasswordTextField() {
        passwordTextField = new JPasswordField();
        passwordTextField.setFont(FONT);
        passwordTextField.setPreferredSize(new Dimension(300, 50));
        passwordTextField.addActionListener(e -> submit());
    }

    private void submit() {
        if (!Arrays.equals(TEST_PASSWORD, passwordTextField.getPassword())) {
            return;
        }
        setVisible(false);
        dispose();
        new DeveloperDialog().setVisible(true);
    }
}
