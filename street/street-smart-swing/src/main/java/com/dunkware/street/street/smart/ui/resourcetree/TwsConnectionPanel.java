package com.dunkware.street.street.smart.ui.resourcetree;


import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TwsConnectionPanel extends JPanel {
    private JTextField nameField;
    private JTextField hostField;
    private JTextField portField;
    private JTextField clientIdField;
    private JButton connectButton;
    private JButton cancelButton;

    public TwsConnectionPanel() {
        setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Connection Name:");
        nameField = new JTextField();
        JLabel hostLabel = new JLabel("Host:");
        hostField = new JTextField();
        JLabel portLabel = new JLabel("Port:");
        portField = new JTextField();
        JLabel clientIdLabel = new JLabel("Client ID:");
        clientIdField = new JTextField();

        connectButton = new JButton("Connect");
        cancelButton = new JButton("Cancel");

        add(nameLabel);
        add(nameField);
        add(hostLabel);
        add(hostField);
        add(portLabel);
        add(portField);
        add(clientIdLabel);
        add(clientIdField);
        add(connectButton);
        add(cancelButton);
    }

    public String getNameField() {
        return nameField.getText();
    }

    public String getHostField() {
        return hostField.getText();
    }

    public int getPortField() {
        return Integer.parseInt(portField.getText());
    }

    public int getClientIdField() {
        return Integer.parseInt(clientIdField.getText());
    }

    public void addConnectButtonListener(ActionListener listener) {
        connectButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }
}