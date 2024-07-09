package com.dunkware.street.street.smart.ui.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.dunkware.street.street.smart.ui.MainFrame;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField userTxt;
	private JTextField serverTxt;
	private JPasswordField passwordTxt;
	private JButton btnNewButton;

	
	private MainFrame mainFrame;
	private LoginDialog dlg; 
	/**
	 * 
	 * Create the panel.
	 */
	public LoginPanel(LoginDialog dlg, MainFrame mainFrame) {
		setOpaque(false);
		this.mainFrame = mainFrame;
		this.dlg = dlg;
		//setBackground(new Color(0, 128, 0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {20, 20, 30, 30, 30, 30, 0, 30, 0, 30, 30, 30, 30, 0, 0, 30, 30, 30, 50, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{100, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel_2 = new JLabel("Street URL. ");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(new Color(172, 196, 206));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		serverTxt = new JTextField();
		serverTxt.setForeground(new Color(128, 128, 128));
		serverTxt.setFont(new Font("Kohinoor Bangla", Font.PLAIN, 12));
		serverTxt.setBorder(new LineBorder(new Color(255, 250, 250), 1, true));
		serverTxt.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_serverTxt = new GridBagConstraints();
		gbc_serverTxt.ipadx = 5;
		gbc_serverTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_serverTxt.gridwidth = 11;
		gbc_serverTxt.insets = new Insets(0, 0, 5, 5);
		gbc_serverTxt.gridx = 7;
		gbc_serverTxt.gridy = 2;
		add(serverTxt, gbc_serverTxt);
		serverTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Street User. ");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(new Color(172, 196, 206));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 5;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		userTxt = new JTextField();
		userTxt.setForeground(new Color(128, 128, 128));
		userTxt.setFont(UIManager.getFont("List.font"));
		userTxt.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		userTxt.setBackground(UIManager.getColor("ComboBox.selectionForeground"));
		GridBagConstraints gbc_userTxt = new GridBagConstraints();
		gbc_userTxt.ipadx = 10;
		gbc_userTxt.gridwidth = 11;
		gbc_userTxt.insets = new Insets(0, 0, 5, 5);
		gbc_userTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_userTxt.gridx = 7;
		gbc_userTxt.gridy = 3;
		add(userTxt, gbc_userTxt);
		userTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Street Pass. ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblNewLabel.setForeground(new Color(172, 196, 206));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 4;
		add(lblNewLabel, gbc_lblNewLabel);
		repaint();
		
		passwordTxt = new JPasswordField();
		passwordTxt.setForeground(new Color(128, 128, 128));
		passwordTxt.setFont(UIManager.getFont("List.font"));
		passwordTxt.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		passwordTxt.setBackground(UIManager.getColor("ComboBox.selectionForeground"));
		GridBagConstraints gbc_passwordTxt = new GridBagConstraints();
		gbc_passwordTxt.gridwidth = 11;
		gbc_passwordTxt.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTxt.gridx = 7;
		gbc_passwordTxt.gridy = 4;
		add(passwordTxt, gbc_passwordTxt);
		
		btnNewButton = new JButton("LOGIN");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnNewButton.setBackground(new Color(172, 196, 206));
		btnNewButton.setMnemonic(KeyEvent.VK_ENTER);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authenticate();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.ipadx = 5;
		gbc_btnNewButton.weightx = 1.0;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridwidth = 7;
		gbc_btnNewButton.gridx = 11;
		gbc_btnNewButton.gridy = 5;
		add(btnNewButton, gbc_btnNewButton);

	}
	
	 private void authenticate() {
	        String server = serverTxt.getText();
	        String user = userTxt.getText();
	        String password = new String(passwordTxt.getPassword());

	        // Replace with actual authentication service call
	        //if (authenticateWithServer(server, user, password)) {
	           // X); // Close the dialog on successful authentication
dlg.dispose();
//mainFrame.setVisible(true);

	            // Show the main application frame
	            SwingUtilities.invokeLater(() -> {
	              mainFrame.initComponents();
	              mainFrame.setVisible(true);
	            });
	       
	    
	 }


}
