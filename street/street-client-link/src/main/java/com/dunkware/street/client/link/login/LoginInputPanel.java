package com.dunkware.street.client.link.login;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Color;

public class LoginInputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField srvTxtField;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JTextField passwordTxt;

	/**
	 * Create the panel.
	 */
	public LoginInputPanel() {
	//	setBackground(new Color(0, 128, 0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblNewLabel = new JLabel("Gateway:");
		//lblNewLabel.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 4;
		add(lblNewLabel, gbc_lblNewLabel);
		
		srvTxtField = new JTextField();
		srvTxtField.setText("https://gateway.dunkstreet.com");
		GridBagConstraints gbc_srvTxtField = new GridBagConstraints();
		gbc_srvTxtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_srvTxtField.weightx = 50.0;
		gbc_srvTxtField.insets = new Insets(0, 0, 5, 5);
		gbc_srvTxtField.gridx = 4;
		gbc_srvTxtField.gridy = 4;
		add(srvTxtField, gbc_srvTxtField);
		srvTxtField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
	//	lblNewLabel_1.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 5;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 5;
		add(textField, gbc);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		//lblNewLabel_2.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 6;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		passwordTxt = new JTextField();
		GridBagConstraints gbc_passwordTxt = new GridBagConstraints();
		gbc_passwordTxt.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTxt.gridx = 4;
		gbc_passwordTxt.gridy = 6;
		add(passwordTxt, gbc_passwordTxt);
		passwordTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnNewButton.setActionCommand("jkj");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 7;
		add(btnNewButton, gbc_btnNewButton);

	}

}
