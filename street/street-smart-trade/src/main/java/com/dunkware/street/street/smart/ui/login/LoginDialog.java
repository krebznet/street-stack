package com.dunkware.street.street.smart.ui.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;

import com.dunkware.street.street.smart.ui.MainFrame;


public class LoginDialog extends JDialog {
	
	private MainFrame mainFrame;
	
    private JLabel errorLabel;

    private ApplicationContext ac; 
    
    @Autowired
    private ResourceLoader rloader;
    
    public LoginDialog(MainFrame frame, ApplicationContext ac) {
    	this.ac = ac;
    	this.ac.getAutowireCapableBeanFactory().autowireBean(this);
    	//.LoginDialog.(frame);
    	this.mainFrame = frame;
    	
    	
    	
       // super(parent, "Login", true);
        setUndecorated(true); // Remove window borders
        setSize(611, 376);
       setLocationRelativeTo(frame);

        // Main panel with background image
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("loginback.png"));
              Image img = icon.getImage();
               g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 100));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridwidth = 30;
        gc.gridheight = 10;

        // Center panel to hold the login panel and error label
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        centerPanel.add(errorLabel, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel);
        
       LoginPanel loginPanel = new LoginPanel(this,mainFrame);
        mainPanel.add(loginPanel, BorderLayout.CENTER);

        pack();
        setSize(611, 376);
        setVisible(true);
       setLocationRelativeTo(frame);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
          //  LoginDialog dialog = new LoginDialog(LoginDialog.this.mainFrame);
          //  dialog.setVisible(true);
        });
    }
    
}


