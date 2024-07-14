package com.dunkware.street.street.smart.ui.viewers;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.dunkware.street.stream.model.trade.TradeContextMetrics;
import com.dunkware.street.stream.model.trade.TradeSet;
//TODO: AVINASHANV-35 Trade Session/Context UI 
/**
 * all i care about is starting a trade bot in the client app and having a UI that views the trade session
 * this will show open trades, closed trades unrealized gain loss, active capital, event log and its the way
 * i start debugging and running bots. as i define more tradeBots in script they will show up as options
 * to run in the client. 
 * 
 * THE KEY PART here is that using the event node tree this UI component can be reused on multiple levels
 * it can be used to view a single trade strategy, then a trade session with a list of trade strategies
 * that have all trades from all strategies, then an broker account that has all trades from all trade sessions
 * hopefully you astarting to see it. 
 *  
 */
// TradeViewer Class
public class TradeViewer extends JInternalFrame {
    private JTabbedPane tabbedPane;
    private JTable tradeTable;
    private TradeTableModel tradeTableModel;
    private JPanel summaryPanel;
    private EventLogger eventLogger;

    public TradeViewer(TradeSet tradeSet, EventLogger eventLogger) {
        super("Trade Viewer", true, true, true, true);
        this.eventLogger = eventLogger;
        setSize(600, 400);
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        tradeTableModel = new TradeTableModel(tradeSet.getTrades());
        tradeTable = new JTable(tradeTableModel);
        tabbedPane.addTab("All Trades", new JScrollPane(tradeTable));

        summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        updateSummaryPanel(tradeSet.getMetrics());

        tabbedPane.addTab("Summary", summaryPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void updateSummaryPanel(TradeContextMetrics metrics) {
        summaryPanel.removeAll();
        summaryPanel.add(new JLabel("Active Trades: " + metrics.getActiveTradeCount()));
        summaryPanel.add(new JLabel("Traded Capital: " + metrics.getTradedCapital()));
        summaryPanel.add(new JLabel("Realized Gain/Loss: " + metrics.getRealizedGainLoss()));
        summaryPanel.add(new JLabel("Unrealized Gain/Loss: " + metrics.getUnrealizedGainLoss()));
        summaryPanel.add(new JLabel("Active Order Count: " + metrics.getActiveOrderCount()));
        summaryPanel.revalidate();
        summaryPanel.repaint();
    }
}
