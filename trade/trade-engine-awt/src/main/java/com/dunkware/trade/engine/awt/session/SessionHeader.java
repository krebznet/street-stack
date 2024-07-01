package com.dunkware.trade.engine.awt.session;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class SessionHeader extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SessionHeader() {
		setBackground(new Color(0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Trade Bot Test Name");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		add(lblNewLabel);

	}

}
