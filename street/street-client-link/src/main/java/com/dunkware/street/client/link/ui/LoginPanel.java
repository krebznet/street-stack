package com.dunkware.street.client.link.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JComboBox<String> streetGatewayField;
    private JTextField streetUsernameField;
    private JPasswordField streetPasswordField;
    private JTextField twsApiServerField;
    private JTextField twsApiPortField;
    private JCheckBox saveLoginInfoCheckBox;
    private JButton loginButton;
    private JLabel errorMessageLabel;
    private GridBagConstraints gbc_1;
    private GridBagConstraints gbc_2;
    private GridBagConstraints gbc_3;
    private GridBagConstraints gbc_4;
    private GridBagConstraints gbc_5;

    public LoginPanel() {
        this(null);
    }

    public LoginPanel(LoginBean loginBean) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{0.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel streetGatewayLabel = new JLabel("Street Cloud:");
        streetGatewayField = new JComboBox<>(new String[] {
            "http://localhost:8085",
            "https://test.dunkstreet.com",
            "https://api.dunkstreet.com"
        });
        JLabel streetUsernameLabel = new JLabel("Street User");
        streetUsernameField = new JTextField(20);
        JLabel streetPasswordLabel = new JLabel("Street Password:");
        streetPasswordField = new JPasswordField(20);
        JLabel twsApiServerLabel = new JLabel("Tws Api Server:");
        twsApiServerField = new JTextField(20);
        JLabel twsApiPortLabel = new JLabel("Tws Api Port:");
        twsApiPortField = new JTextField(20);

        twsApiPortField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = ((JTextField) input).getText();
                try {
                    Integer.parseInt(text);
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(input, "Please enter a valid number for the port.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        });

        saveLoginInfoCheckBox = new JCheckBox("Save Login Info");

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        errorMessageLabel = new JLabel("");
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(streetGatewayLabel, gbc);
        gbc_5 = new GridBagConstraints();
        gbc_5.anchor = GridBagConstraints.WEST;
        gbc_5.gridx = 1;
        gbc_5.gridy = 0;
        add(streetGatewayField, gbc_5);
        gbc_3 = new GridBagConstraints();
        gbc_3.anchor = GridBagConstraints.WEST;
        gbc_3.gridx = 0;
        gbc_3.gridy = 1;
        add(streetUsernameLabel, gbc_3);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(streetUsernameField, gbc);
        gbc_4 = new GridBagConstraints();
        gbc_4.anchor = GridBagConstraints.WEST;
        gbc_4.gridx = 0;
        gbc_4.gridy = 2;
        add(streetPasswordLabel, gbc_4);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(streetPasswordField, gbc);
        gbc_1 = new GridBagConstraints();
        gbc_1.anchor = GridBagConstraints.WEST;
        gbc_1.gridx = 0;
        gbc_1.gridy = 3;
        add(twsApiServerLabel, gbc_1);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(twsApiServerField, gbc);
        gbc_2 = new GridBagConstraints();
        gbc_2.anchor = GridBagConstraints.WEST;
        gbc_2.gridx = 0;
        gbc_2.gridy = 4;
        add(twsApiPortLabel, gbc_2);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(twsApiPortField, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        add(saveLoginInfoCheckBox, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(errorMessageLabel, gbc);

        if (loginBean != null) {
            populateFields(loginBean);
        }
    }

    private void handleLogin() {
        if (validateFields()) {
            String streetGateway = (String) streetGatewayField.getSelectedItem();
            String streetUsername = streetUsernameField.getText();
            char[] streetPassword = streetPasswordField.getPassword();
            String twsApiServer = twsApiServerField.getText();
            String twsApiPort = twsApiPortField.getText();
            boolean saveLoginInfo = saveLoginInfoCheckBox.isSelected();

            try {
                // Implement your login logic here
                // For example:
                // loginService.login(streetGateway, streetUsername, new String(streetPassword), twsApiServer, Integer.parseInt(twsApiPort));

                // If login is successful, hide the error message
                errorMessageLabel.setVisible(false);

                // Continue with the application logic
            } catch (Exception e) {
                // If an error occurs during login, display the error message
                errorMessageLabel.setText("Login failed: " + e.getMessage());
                errorMessageLabel.setVisible(true);
            }
        }
    }

    private boolean validateFields() {
        if (streetUsernameField.getText().trim().isEmpty() ||
            streetPasswordField.getPassword().length == 0 ||
            twsApiServerField.getText().trim().isEmpty()) {
            errorMessageLabel.setText("All fields must be filled except for Tws Api Port.");
            errorMessageLabel.setVisible(true);
            return false;
        }
        return true;
    }

    private void populateFields(LoginBean loginBean) {
        streetGatewayField.setSelectedItem(loginBean.getStreetGateway());
        streetUsernameField.setText(loginBean.getStreetUsername());
        streetPasswordField.setText(loginBean.getStreetPassword());
        twsApiServerField.setText(loginBean.getTwsApiServer());
        twsApiPortField.setText(loginBean.getTwsApiPort());
        saveLoginInfoCheckBox.setSelected(loginBean.isSaveLoginInfo());
    }

    public static class LoginBean {
        private String streetGateway;
        private String streetUsername;
        private String streetPassword;
        private String twsApiServer;
        private String twsApiPort;
        private boolean saveLoginInfo;

        // Getters and Setters
        public String getStreetGateway() {
            return streetGateway;
        }

        public void setStreetGateway(String streetGateway) {
            this.streetGateway = streetGateway;
        }

        public String getStreetUsername() {
            return streetUsername;
        }

        public void setStreetUsername(String streetUsername) {
            this.streetUsername = streetUsername;
        }

        public String getStreetPassword() {
            return streetPassword;
        }

        public void setStreetPassword(String streetPassword) {
            this.streetPassword = streetPassword;
        }

        public String getTwsApiServer() {
            return twsApiServer;
        }

        public void setTwsApiServer(String twsApiServer) {
            this.twsApiServer = twsApiServer;
        }

        public String getTwsApiPort() {
            return twsApiPort;
        }

        public void setTwsApiPort(String twsApiPort) {
            this.twsApiPort = twsApiPort;
        }

        public boolean isSaveLoginInfo() {
            return saveLoginInfo;
        }

        public void setSaveLoginInfo(boolean saveLoginInfo) {
            this.saveLoginInfo = saveLoginInfo;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginBean bean = new LoginBean();
        bean.setSaveLoginInfo(true);
        bean.setStreetGateway("https://api.dunkstreet.com");
        bean.setStreetPassword("ABC");
        bean.setStreetUsername("dunkman");
        frame.getContentPane().add(new LoginPanel(bean));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

