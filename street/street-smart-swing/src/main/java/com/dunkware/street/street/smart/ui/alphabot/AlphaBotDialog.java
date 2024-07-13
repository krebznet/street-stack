package com.dunkware.street.street.smart.ui.alphabot;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.dunkware.street.stream.model.alphabot.AlphaBotInput;

public class AlphaBotDialog extends JDialog {

	private JTextField nameField;
    private JComboBox<String> strategyComboBox;
    private JTextField twsHostField;
    private JTextField twsPortField;
    private JTextField twsClientIdField;
    private JLabel validationLabel;

    
    private AlphaBotInput input;

    public AlphaBotDialog(Window parent, AlphaBotInput input) {
    	 super(parent, "AlphaBot Wizard");
         this.input = input;

         // Create content panel with GridBagLayout
         JPanel contentPanel = new JPanel(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
         gbc.fill = GridBagConstraints.HORIZONTAL;

         // Add name label and text field
         JLabel nameLabel = new JLabel("Bot Name:");
         gbc.gridx = 0;
         gbc.gridy = 0;
         contentPanel.add(nameLabel, gbc);

         nameField = new JTextField(20);
         gbc.gridx = 1;
         gbc.gridy = 0;
         contentPanel.add(nameField, gbc);

         // Add strategy label and combo box
         JLabel strategyLabel = new JLabel("Bot Type:");
         gbc.gridx = 0;
         gbc.gridy = 1;
         contentPanel.add(strategyLabel, gbc);

         String[] strategies = { "Strategy1", "Strategy2", "Strategy3" }; // Mock data
         strategyComboBox = new JComboBox<>(strategies);
         gbc.gridx = 1;
         gbc.gridy = 1;
         contentPanel.add(strategyComboBox, gbc);

         // Add TWS Host label and text field
         JLabel twsHostLabel = new JLabel("TWS Host:");
         gbc.gridx = 0;
         gbc.gridy = 2;
         contentPanel.add(twsHostLabel, gbc);

         twsHostField = new JTextField(20);
         gbc.gridx = 1;
         gbc.gridy = 2;
         contentPanel.add(twsHostField, gbc);

         // Add TWS Port label and text field
         JLabel twsPortLabel = new JLabel("TWS Port:");
         gbc.gridx = 0;
         gbc.gridy = 3;
         contentPanel.add(twsPortLabel, gbc);

         twsPortField = new JTextField(20);
         gbc.gridx = 1;
         gbc.gridy = 3;
         contentPanel.add(twsPortField, gbc);

         // Add TWS Client ID label and text field
         JLabel twsClientIdLabel = new JLabel("TWS Client ID:");
         gbc.gridx = 0;
         gbc.gridy = 4;
         contentPanel.add(twsClientIdLabel, gbc);

         twsClientIdField = new JTextField(20);
         gbc.gridx = 1;
         gbc.gridy = 4;
         contentPanel.add(twsClientIdField, gbc);

         // Add validation label
         validationLabel = new JLabel("");
         validationLabel.setForeground(Color.RED);
         gbc.gridx = 0;
         gbc.gridy = 5;
         gbc.gridwidth = 2;
         contentPanel.add(validationLabel, gbc);

         // Add login button
         JButton loginButton = new JButton("Save");
         loginButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if (validateFields()) {
                     saveBotInput();
                     dispose();
                 }
             }
         });
         gbc.gridx = 1;
         gbc.gridy = 6;
         gbc.anchor = GridBagConstraints.EAST;
         contentPanel.add(loginButton, gbc);

         // Add content panel to the dialog
         getContentPane().add(contentPanel, BorderLayout.CENTER);

         // Set dialog properties
         pack();
         setLocationRelativeTo(parent);
     }

  

    private boolean validateFields() {
    	String name = nameField.getText().trim();
        String twsHost = twsHostField.getText().trim();
        String twsPortStr = twsPortField.getText().trim();
        String twsClientIdStr = twsClientIdField.getText().trim();

        if (name.isEmpty() || twsHost.isEmpty() || twsPortStr.isEmpty() || twsClientIdStr.isEmpty()) {
            validationLabel.setText("All fields must be filled out.");
            return false;
        }

        try {
            int twsPort = Integer.parseInt(twsPortStr);
            int twsClientId = Integer.parseInt(twsClientIdStr);
        } catch (NumberFormatException e) {
            validationLabel.setText("TWS Port and Client ID must be numeric.");
            return false;
        }

        validationLabel.setText("");
        return true;
    }

   

    private void saveBotInput() {
        input.setName(nameField.getText().trim());
        input.setStrategy((String) strategyComboBox.getSelectedItem());
        input.setTwsHost(twsHostField.getText().trim());
        input.setTwsPort(Integer.parseInt(twsPortField.getText().trim()));
        input.setTwsClientId(Integer.parseInt(twsClientIdField.getText().trim()));
    }
    public AlphaBotInput getAlphaBotInput() {
        return input;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setVisible(true);

                AlphaBotDialog dialog = new AlphaBotDialog(frame, new AlphaBotInput());
                dialog.setVisible(true);
                AlphaBotInput input = dialog.getAlphaBotInput();
                System.out.println("AlphaBotInput: " + input.getName() + ", " + input.getStrategy());
            }
        });
    }
}
