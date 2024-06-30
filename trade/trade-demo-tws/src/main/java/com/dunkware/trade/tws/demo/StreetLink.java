package com.dunkware.trade.tws.demo;

import javax.swing.*;

import com.dunkware.trade.tws.demo.panels.TradeBotViewPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class StreetLink extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField twsHostField;
    private JTextField twsPortField;
    private JCheckBox rememberDetailsCheckbox;
    private JLabel statusBar;

    public StreetLink() {
        // Frame properties
        setTitle("Street Smart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create menu bar
        createMenuBar();

        // CardLayout for switching between login and main interface
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create login panel
        mainPanel.add(createLoginPanel(), "loginPanel");

        // Create main interface panel
        mainPanel.add(createMainInterfacePanel(), "mainInterfacePanel");

        add(mainPanel);

        // Create status bar
        createStatusBar();

        // Set initial status
        updateStatusBar("Street Login");
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.setMnemonic(KeyEvent.VK_L);
        logoutMenuItem.setToolTipText("Logout and return to login screen");
        logoutMenuItem.addActionListener(e -> {
            cardLayout.show(mainPanel, "loginPanel");
            updateStatusBar("Street Login");
        });
        fileMenu.add(logoutMenuItem);

        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.setMnemonic(KeyEvent.VK_Q);
        quitMenuItem.setToolTipText("Exit application");
        quitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(quitMenuItem);

        menuBar.add(fileMenu);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        aboutMenuItem.setToolTipText("Show application information");
        aboutMenuItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private void showAboutDialog() {
        String aboutMessage = "<html><center>"
                + "Street Link<br>"
                + "Version 1.0.0<br>"
                + "Release Date: 2024-06-20<br><br>"
                + "<small>Copyright 2024 Dunkware Software All Rights Reserved.<br>"
                + "Use of this application is bound to the user agreement.<br>"
                + "<a href=\"https://agreement.dunkstreet.com\">https://agreement.dunkstreet.com</a></small>"
                + "</center></html>";
        JOptionPane.showMessageDialog(this, aboutMessage, "About Street Link", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(new Color(0, 100, 0));  // Darker green background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Header
        JLabel headerLabel = new JLabel("STREET LINK");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginPanel.add(headerLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        usernameField = new JTextField(20);
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            // Placeholder for login validation
            if (validateLogin(usernameField.getText(), new String(passwordField.getPassword()))) {
                cardLayout.show(mainPanel, "mainInterfacePanel");
                updateStatusBar("Tienes Connection");
                JOptionPane.showMessageDialog(this, "Welcome Amigo, with great power comes great responsibility.", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        loginPanel.add(loginButton, gbc);

        // Footer
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, -5, 50, 50); // Move text 50px to the right
        JLabel footerLabel = new JLabel("<html><center>"
                + "<small>Copyright 2024 Dunkware Software All Rights Reserved.<br>"
                + "Use of this application is bound to the user agreement.<br>"
                + "<a href=\"https://agreement.dunkstreet.com\" style=\"color:white;\">https://agreement.dunkstreet.com</a></small>"
                + "</center></html>");
        footerLabel.setForeground(Color.WHITE);
        loginPanel.add(footerLabel, gbc);

        return loginPanel;
    }

    private boolean validateLogin(String username, String password) {
        // Placeholder logic for login validation
        // Replace this with actual validation logic
    	
        return "user".equals(username) && "password".equals(password);
    }

    private JPanel createMainInterfacePanel() {
    	TradeBotViewPanel pot = new TradeBotViewPanel();
    	pot.setVisible(true);
    	if(1 == 1)
    		return pot;
        JPanel mainInterfacePanel = new JPanel(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("TWS", createHomeTab());
        tabbedPane.addTab("DASH", new JPanel()); // Placeholder
        tabbedPane.addTab("BOTS", new JPanel());  // Placeholder
        tabbedPane.addTab("CONSOLE", new JPanel());  // Placeholder

        mainInterfacePanel.add(tabbedPane, BorderLayout.CENTER);

        return mainInterfacePanel;
    }

    private JPanel createHomeTab() {
        JPanel homeTab = new JPanel();
        homeTab.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel twsHostLabel = new JLabel("Tws Host:");
        homeTab.add(twsHostLabel, gbc);

        gbc.gridx = 1;
        twsHostField = new JTextField(20);
        homeTab.add(twsHostField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel twsPortLabel = new JLabel("Tws Port:");
        homeTab.add(twsPortLabel, gbc);

        gbc.gridx = 1;
        twsPortField = new JTextField(20);
        homeTab.add(twsPortField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        rememberDetailsCheckbox = new JCheckBox("Remember Connection Details");
        homeTab.add(rememberDetailsCheckbox, gbc);

        return homeTab;
    }

    private void createStatusBar() {
        statusBar = new JLabel("Street Login");
       
        statusBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(statusBar, BorderLayout.SOUTH);
    }

    private void updateStatusBar(String status) {
        statusBar.setText(status);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StreetLink().setVisible(true));
    }
}
