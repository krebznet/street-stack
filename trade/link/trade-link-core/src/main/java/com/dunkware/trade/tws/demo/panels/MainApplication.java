package com.dunkware.trade.tws.demo.panels;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainApplication {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dunktrade");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            StreetLinkToolbar toolBar = new StreetLinkToolbar();  // The toolbar at the top
            TradeBotViewPanel tradeBotViewPanel = new TradeBotViewPanel();  // The main content panel

            frame.setLayout(new BorderLayout());
            frame.add(toolBar, BorderLayout.NORTH);
            frame.add(tradeBotViewPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }

}
