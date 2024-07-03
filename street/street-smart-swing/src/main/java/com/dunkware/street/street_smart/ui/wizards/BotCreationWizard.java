package com.dunkware.street.street_smart.ui.wizards;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class BotCreationWizard extends JDialog {
    private JComboBox<String> strategyComboBox;
    private JComboBox<String> accountComboBox;
    private JTextField botNameField;

    public BotCreationWizard(JFrame parent) {
        super(parent, "Create New Bot", true);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Wizard panel
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Bot Name:"));
        botNameField = new JTextField();
        panel.add(botNameField);

        panel.add(new JLabel("Strategy:"));
        strategyComboBox = new JComboBox<>(new String[]{"Strategy 1", "Strategy 2"});
        panel.add(strategyComboBox);

        panel.add(new JLabel("Account:"));
        accountComboBox = new JComboBox<>(new String[]{"Account 1", "Account 2"});
        panel.add(accountComboBox);

        add(panel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBot();
            }
        });
        buttonPanel.add(createButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createBot() {
        String botName = botNameField.getText();
        String strategy = (String) strategyComboBox.getSelectedItem();
        String account = (String) accountComboBox.getSelectedItem();

        // Use the runtime controller to create and manage the bot
      //  RuntimeController.getInstance().createBot(botName, strategy, account);

        dispose();
    }
}