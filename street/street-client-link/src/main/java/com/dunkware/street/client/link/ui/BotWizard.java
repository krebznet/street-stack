package com.dunkware.street.client.link.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class BotWizard extends JFrame {
    private JTextField botNameField, capitalAllocationField;
    private JComboBox<String> accountDropdown, strategyDropdown;
    private JPanel tradeAllocationPanel;
    private Map<String, Double> tradeAllocations = new HashMap<>();

    public BotWizard() {
        setTitle("Bot Wizard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: Bot Configuration
        JPanel botConfigPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 0, 2, 0); // Reduce vertical spacing

        botConfigPanel.add(new JLabel("Bot Name:"), gbc);
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 0, 2, 0); // Reduce vertical spacing
        botNameField = new JTextField(15);
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 0, 2, 0); // Reduce vertical spacing
        botConfigPanel.add(botNameField, gbc);
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 0, 2, 0); // Reduce vertical spacing
        botConfigPanel.add(new JLabel("Capital Allocation:"), gbc);
        capitalAllocationField = new JTextField(15);
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 0, 2, 0); // Reduce vertical spacing
        botConfigPanel.add(capitalAllocationField, gbc);
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 0, 2, 0); // Reduce vertical spacing
        botConfigPanel.add(new JLabel("Account:"), gbc);
        accountDropdown = new JComboBox<>(new String[]{"Account 1", "Account 2", "Account 3"}); // Replace with actual account data
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 0, 2, 0); // Reduce vertical spacing
        botConfigPanel.add(accountDropdown, gbc);

        tabbedPane.addTab("Bot Configuration", botConfigPanel);

        // Tab 2: Bot Strategy
        JPanel strategyPanel = new JPanel(new BorderLayout());
        strategyDropdown = new JComboBox<>(new String[]{"Strategy 1", "Strategy 2"}); // Replace with actual strategy data
        strategyDropdown.addActionListener(this::strategySelected);
        strategyPanel.add(strategyDropdown, BorderLayout.NORTH);

        tradeAllocationPanel = new JPanel();
        tradeAllocationPanel.setLayout(new BoxLayout(tradeAllocationPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(tradeAllocationPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        strategyPanel.add(scrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("Bot Strategy", strategyPanel);

        // Common bottom panel
        JPanel bottomPanel = new JPanel();
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose()); // Closes the wizard
        JButton createButton = new JButton("Create");
        createButton.addActionListener(this::createBot); // Placeholder for create action
        bottomPanel.add(cancelButton);
        bottomPanel.add(createButton);

        // Adding components to the frame
        add(tabbedPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void strategySelected(ActionEvent e) {
    	 tradeAllocationPanel.removeAll();
         // Example adding trades dynamically, closely spaced vertically
         for (int i = 1; i <= 25; i++) {
             JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2)); // Reduce vertical spacing in FlowLayout
             JLabel tradeLabel = new JLabel("Trade " + i);
             JTextField tradeField = new JTextField(10);
             rowPanel.add(tradeLabel);
             rowPanel.add(tradeField);
             tradeAllocationPanel.add(rowPanel);
             tradeAllocations.put("Trade " + i, 0.0); // Initialize map
         }
         tradeAllocationPanel.revalidate();
         tradeAllocationPanel.repaint();
    }

    private void createBot(ActionEvent e) {
        // Example action for Create button
        System.out.println("Create button clicked");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BotWizard().setVisible(true));
    }
}
