package com.dunkware.street.street.smart.ui.resourcetree;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountTradeBotPanel extends JPanel {
    private JTextField botNameField;
    private JComboBox<String> botTypeComboBox;
    private JButton createButton;
    private JButton cancelButton;

    public AccountTradeBotPanel() {
        setLayout(new GridLayout(3, 2));

        JLabel botNameLabel = new JLabel("Bot Name:");
        botNameField = new JTextField();
        JLabel botTypeLabel = new JLabel("Bot Type:");
        botTypeComboBox = new JComboBox<>(new String[]{"Bot 1", "Bot 2", "Bot 3"});

        createButton = new JButton("Create");
        cancelButton = new JButton("Cancel");

        add(botNameLabel);
        add(botNameField);
        add(botTypeLabel);
        add(botTypeComboBox);
        add(createButton);
        add(cancelButton);
    }

    public String getBotName() {
        return botNameField.getText();
    }

    public String getBotType() {
        return (String) botTypeComboBox.getSelectedItem();
    }

    public void addCreateButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }
}