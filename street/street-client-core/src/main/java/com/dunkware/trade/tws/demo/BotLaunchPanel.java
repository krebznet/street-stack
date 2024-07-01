package com.dunkware.trade.tws.demo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotLaunchPanel extends JPanel {

    public BotLaunchPanel() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        JButton launchButton = new JButton();

        // Set fixed size for the button
        launchButton.setPreferredSize(new Dimension(500, 500));
        
        // Placeholder for setting an icon on the button
        // Uncomment the following line and replace "path/to/icon.png" with the actual path
         launchButton.setIcon(new ImageIcon(this.getClass().getResource("fuseme.png")));

        // Placeholder for click handler
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle click event here
                System.out.println("Button clicked!");
            }
        });

        // Add the button to the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(launchButton, gbc);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bot Launch Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BotLaunchPanel());
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
