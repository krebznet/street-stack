package com.dunkware.trade.tws.demo.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class TradeDetailsPanel extends JPanel {
    public TradeDetailsPanel() {
        super(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Create tabs
        tabbedPane.addTab("Summary", createSummaryPanel());
        tabbedPane.addTab("Orders", createOrdersPanel());
        tabbedPane.addTab("Event Log", createEventLogPanel());

        // Add tabbedPane to the main panel
        this.add(tabbedPane, BorderLayout.CENTER);

        // Close button at the bottom
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this::closePanel);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Summary information goes here...")); // Placeholder
        return panel;
    }

    private JPanel createOrdersPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Orders information goes here...")); // Placeholder
        return panel;
    }

    private JPanel createEventLogPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Event log information goes here...")); // Placeholder
        return panel;
    }

    private void closePanel(ActionEvent e) {
        Component component = (Component) e.getSource();
        Window window = SwingUtilities.getWindowAncestor(component);
        window.dispose();
    }

    // Method to show the details panel in a dialog
    public static void showDialog(Component parent, String title) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().add(new TradeDetailsPanel());
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
