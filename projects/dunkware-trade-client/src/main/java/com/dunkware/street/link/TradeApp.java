package com.dunkware.street.link;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TradeApp {

	private JFrame frmDunktrade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TradeApp window = new TradeApp();
					window.frmDunktrade.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TradeApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDunktrade = new JFrame();
		frmDunktrade.setTitle("Street Link");
		frmDunktrade.setBounds(100, 100, 450, 300);
		frmDunktrade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TradeLogin l = new TradeLogin();
				
			}
		});
		frmDunktrade.getContentPane().add(btnNewButton, BorderLayout.WEST);
	}

}
