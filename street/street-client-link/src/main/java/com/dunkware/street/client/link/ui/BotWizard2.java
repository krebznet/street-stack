package com.dunkware.street.client.link.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BotWizard2 extends JFrame {
    private JTextField botNameField, capitalAllocationField;
    private JComboBox<String> accountDropdown, strategyDropdown;
    private JTextArea strategyDescription;
    private JPanel tradeAllocationPanel;
    private Map<String, Double> tradeAllocations = new HashMap<>();

    public BotWizard2() {
        setTitle("Bot Wizard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: Bot Configuration (No changes in this segment)
        // ...

        // Tab 2: Bot Strategy
        JPanel strategyPanel = new JPanel(new BorderLayout(10, 10));
        strategyDropdown = new JComboBox<>(new String[]{"Strategy 1", "Strategy 2"}); // Replace with actual strategy data
        strategyDropdown.addActionListener(this::strategySelected);
        strategyPanel.add(strategyDropdown, BorderLayout.NORTH);

        strategyDescription = new JTextArea(2, 20);
        strategyDescription.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50)); // Fixed height for the description
        strategyDescription.setLineWrap(true);
        strategyDescription.setWrapStyleWord(true);
        strategyDescription.setEditable(false);
        JScrollPane descriptionScrollPane = new JScrollPane(strategyDescription);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        strategyPanel.add(descriptionScrollPane, BorderLayout.CENTER);

        tradeAllocationPanel = new JPanel(new GridLayout(0, 2, 5, 5)); // Dynamic grid with 2 columns
        JScrollPane scrollPane = new JScrollPane(tradeAllocationPanel);
        scrollPane.setPreferredSize(new Dimension(Integer.MAX_VALUE, 200)); // Fixed height for the trade panel
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        strategyPanel.add(scrollPane, BorderLayout.SOUTH);

        tabbedPane.addTab("Bot Strategy", strategyPanel);

        // Common bottom panel (No changes in this segment)
        // ...

        // Adding components to the frame
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void strategySelected(ActionEvent e) {
        strategyDescription.setText("Description of " + strategyDropdown.getSelectedItem());
        tradeAllocationPanel.removeAll();
        for (int i = 1; i <= 6; i++) { // Example for 6 trades, grouped into 2 per row
            JPanel rowPanel = new JPanel();
            JLabel tradeLabel = new JLabel("Trade " + i);
            JTextField tradeField = new JTextField(5);
            rowPanel.add(tradeLabel);
            rowPanel.add(tradeField);
            tradeAllocationPanel.add(rowPanel);
            tradeAllocations.put("Trade " + i, 0.0); // Initialize map with trade names and default allocations
        }
        tradeAllocationPanel.revalidate();
        tradeAllocationPanel.repaint();
    }

    private void createBot(ActionEvent e) {
        System.out.println("Create button clicked"); // Example action
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BotWizard2().setVisible(true));
    }
}
