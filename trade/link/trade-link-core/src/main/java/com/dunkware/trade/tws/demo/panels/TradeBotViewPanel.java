package com.dunkware.trade.tws.demo.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TradeBotViewPanel extends JPanel {
    private DefaultTableModel tradesModel;
    private JTable tradesTable;
    private Timer updateTimer;
    private Random random = new Random();

    public TradeBotViewPanel() {
        super(new BorderLayout());
        initializeUI();
        startDataUpdates();
    }

    private void initializeUI() {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM); // Tabs at the bottom
        tabbedPane.addTab("Summary", createSummaryTab());
        tabbedPane.addTab("Trades", createTradesTab());
        tabbedPane.addTab("Orders", createOrdersTab());
        tabbedPane.addTab("Events", createEventsTab());
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createSummaryTab() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnNames = {"Metric", "Value"};
        Object[][] data = {
            {"Total Trades", 50},
            {"Successful Trades", 45},
            {"Failed Trades", 5},
            {"Profit", "$1,000"}
        };
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createTradesTab() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnNames = {"Play", "Ticker", "Avg Price", "Status", "Realized PL", "Size", "ID", "Commission"};
        tradesModel = new DefaultTableModel(null, columnNames);
        tradesTable = new JTable(tradesModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.BLUE);
                    c.setForeground(Color.WHITE);
                }
                return c;
            }
        };
        
      
        tradesTable.setGridColor(Color.GRAY);
        tradesTable.setShowGrid(true);
        tradesTable.setRowHeight(25);
        addPopupToTable(tradesTable);
        tradesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem detailsItem = new JMenuItem("Show Details");
                    detailsItem.addActionListener(ae -> TradeDetailsPanel.showDialog(tradesTable, "Trade Details"));
                    popupMenu.add(detailsItem);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        panel.add(new JScrollPane(tradesTable), BorderLayout.CENTER);
        return panel;
    }

    private void addPopupToTable(JTable table) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem closeTradeItem = new JMenuItem("Close Trade");
        closeTradeItem.addActionListener(e -> closeTrade(table.getSelectedRow()));
        popupMenu.add(closeTradeItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    private void closeTrade(int row) {
        if (row >= 0) {
            System.out.println("Closing trade at row: " + row);
            // Implement trade closing logic here
        }
    }

    private JPanel createOrdersTab() {
        JPanel panel = new JPanel(new BorderLayout());
        // Placeholder for actual content
        JLabel label = new JLabel("Orders - Under Construction");
        panel.add(label);
        return panel;
    }

    private JPanel createEventsTab() {
        JPanel panel = new JPanel(new BorderLayout());
        // Placeholder for actual content
        JLabel label = new JLabel("Events - Under Construction");
        panel.add(label);
        return panel;
    }

    private void startDataUpdates() {
        updateTimer = new Timer(250, this::simulateDataUpdate);
        updateTimer.start();
    }

    // Method to show the details panel in a dialog
    public static void showDialog(Component parent, String title) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().add(new TradeDetailsPanel());
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
    private void simulateDataUpdate(ActionEvent e) {
        Object[] newRow = {"Buy", "AAPL", 150 + random.nextInt(50), "Open", "$0", 100 + random.nextInt(100), "T" + (100 + random.nextInt(900)), "$10"};
        tradesModel.addRow(newRow);
        int rowToUpdate = random.nextInt(tradesModel.getRowCount());
        tradesModel.setValueAt(150 + random.nextInt(50), rowToUpdate, 2); // Update 'Avg Price'
    }
}
