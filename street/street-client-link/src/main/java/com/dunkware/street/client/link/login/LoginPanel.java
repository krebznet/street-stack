package com.dunkware.street.client.link.login;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel() {
        // Set the layout to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Create the labels and text fields
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        
        // Set common constraints
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Add Username label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(userLabel, gbc);
        
        // Add Username text field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(userText, gbc);
        
        // Add Password label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passLabel, gbc);
        
        // Add Password text field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(passText, gbc);
        
        // Add Login button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);
    }
    
    private static void createAndShowGUI() {
        // Create the frame
        JFrame frame = new JFrame("Login Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and set up the content pane
        LoginPanel newContentPane = new LoginPanel();
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);
        
        // Display the window
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
