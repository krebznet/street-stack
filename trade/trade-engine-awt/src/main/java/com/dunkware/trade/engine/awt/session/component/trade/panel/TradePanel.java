package com.dunkware.trade.engine.awt.session.component.trade.panel;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TradePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TradePanel() {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Trades", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);

	}

}
