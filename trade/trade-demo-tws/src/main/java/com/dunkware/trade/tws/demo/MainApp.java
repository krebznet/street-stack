package com.dunkware.trade.tws.demo;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainApp extends JFrame {
    // Constructor for the main application window
    public MainApp() {
        // Set up the frame
        setTitle("Street Link");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Trades", new JPanel()); // Add a tab with a panel
      
        
        tabbedPane.addTab("Orders", new JPanel());
        tabbedPane.addTab("Logs", new JPanel());

        // Add the tabbed pane to the frame
        add(tabbedPane);

        setVisible(true);
    }

    // Method to show login dialog
    private static boolean showLoginDialog() {
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(null, loginPanel, "Login",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Here you might check the username and password against your user store
        if (result == JOptionPane.OK_OPTION) {
            return true; // Assume login is successful
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // Show login dialog and proceed if login is successful
        if (showLoginDialog()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new MainApp(); // Create and show the main application window
                }
            });
        } else {
            System.exit(0); // Exit if login fails
        }
    }
}