package com.dunkware.trade.tws.demo.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StreetLinkToolbar extends JToolBar {

    public StreetLinkToolbar() {
        super();
        initializeToolBar();
    }

    private void initializeToolBar() {
        // Create and add buttons with icon placeholders
        addButton("Dashboard", "icons/dashboard.png", this::onDashboardClicked);
        addButton("Trade Bots", "icons/trade_bots.png", this::onTradeBotsClicked);
        addButton("Monitor", "icons/monitor.png", this::onMonitorClicked);
        addButton("Log Out", "icons/logout.png", this::onLogOutClicked);

        setFloatable(false); // Optional: make the toolbar non-floatable
    }

    private void addButton(String tooltip, String iconPath, Runnable action) {
        JButton button = new JButton();
        button.setToolTipText(tooltip);
        
        // Set an icon if the path is correct. Placeholder for now.
        // Icon icon = new ImageIcon(getClass().getResource(iconPath));
        // button.setIcon(icon);
        
        button.setText(tooltip); // Using text as placeholder for icons
        button.addActionListener(e -> action.run());
        add(button);
    }

    private void onDashboardClicked() {
        System.out.println("Dashboard clicked");
        // Add functionality to switch to the Dashboard view
    }

    private void onTradeBotsClicked() {
        System.out.println("Trade Bots clicked");
        // Add functionality to switch to the Trade Bots view
    }

    private void onMonitorClicked() {
        System.out.println("Monitor clicked");
        // Add functionality to switch to the Monitor view
    }

    private void onLogOutClicked() {
        System.out.println("Log Out clicked");
        // Add functionality to handle user log out
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Application with Global Toolbar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        StreetLinkToolbar toolBar = new StreetLinkToolbar();
        frame.add(toolBar, BorderLayout.NORTH);

        frame.setVisible(true);
    }
}
