package com.dunkware.trade.tws.demo.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setLayout(new MigLayout("", "[75px,center][4px][140px]", "[26px][26px][]"));
		
		JLabel lblNewLabel = new JLabel("Tws Host:");
		add(lblNewLabel, "cell 0 0 3 1,alignx left,growy");
		
		textField = new JTextField();
		add(textField, "cell 2 0,alignx left,aligny top");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tws Port:");
		add(lblNewLabel_1, "cell 0 1,alignx left,aligny center");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 2 1,alignx left,aligny top");
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Connect");
		add(btnNewButton, "cell 2 2");

	}
}
