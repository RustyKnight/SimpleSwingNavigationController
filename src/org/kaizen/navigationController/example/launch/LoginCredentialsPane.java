/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.launch;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import org.kaizen.navigationController.NavigatableView;
import org.kaizen.navigationController.example.User;

/**
 *
 * @author shane.whitehead
 */
public class LoginCredentialsPane extends JPanel implements NavigatableView {
    
    public interface Observer {
        public void authenticationWasSuccessful(User user);

        public void performUserRegistration();
    }
    private Observer observer;
    private UserAuthenticationService userAuthenticator;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton authenticateButton;
    private JButton registerButton;

    public LoginCredentialsPane(UserAuthenticationService userAuthenticator, Observer observer) {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setBorder(new EmptyBorder(32, 32, 0, 32));
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 48));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        userNameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        authenticateButton = new JButton("Authenticate");
        registerButton = new JButton("Register");
        JPanel credentialsPane = new JPanel(new GridBagLayout());
        credentialsPane.setBorder(new EmptyBorder(8, 8, 4, 8));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 4);
        credentialsPane.add(new JLabel("User name: "), gbc);
        gbc.gridy++;
        credentialsPane.add(new JLabel("Password: "), gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        credentialsPane.add(userNameField, gbc);
        gbc.gridy++;
        credentialsPane.add(passwordField, gbc);
        JPanel actionPane = new JPanel();
        actionPane.setBorder(new EmptyBorder(4, 8, 8, 8));
        actionPane.add(authenticateButton);
        actionPane.add(registerButton);
        add(titleLabel, BorderLayout.NORTH);
        add(credentialsPane);
        add(actionPane, BorderLayout.SOUTH);
        ActionListener authenticationActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                char[] password = passwordField.getPassword();
                userNameField.setEnabled(false);
                passwordField.setEnabled(false);
                authenticateButton.setEnabled(false);
                registerButton.setEnabled(false);
                userAuthenticator.authenticate(userName, password, new UserAuthenticationService.Observer() {
                    @Override
                    public void authenticationWasSucessful(User user) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                observer.authenticationWasSuccessful(user);
                            }
                        });
                    }

                    @Override
                    public void authenticationDidFail() {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(LoginCredentialsPane.this, "Authentication failed", "Error", JOptionPane.ERROR_MESSAGE);
                                userNameField.setEnabled(true);
                                passwordField.setEnabled(true);
                                authenticateButton.setEnabled(true);
                                registerButton.setEnabled(true);
                            }
                        });
                    }
                });
            }
        };
        userNameField.addActionListener(authenticationActionListener);
        passwordField.addActionListener(authenticationActionListener);
        authenticateButton.addActionListener(authenticationActionListener);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.performUserRegistration();
            }
        });
    }

    @Override
    public void willDismissView() {
    }

    @Override
    public void didDismissView() {
        userNameField.setText(null);
        passwordField.setText(null);
    }

    @Override
    public void willPresentView() {
        userNameField.setText(null);
        passwordField.setText(null);
        userNameField.setEnabled(true);
        passwordField.setEnabled(true);
        authenticateButton.setEnabled(true);
        registerButton.setEnabled(true);
    }

    @Override
    public void didPresentView() {
        userNameField.requestFocusInWindow();
    }
    
}
