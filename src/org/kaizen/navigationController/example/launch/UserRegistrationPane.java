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

/**
 *
 * @author shane.whitehead
 */
public class UserRegistrationPane extends JPanel {    
    public interface Observer {
        public void didRegisterNewUser();
        public void didCancelUserRegistration();
    }
    
    private Observer observer;
    private UserRegistrationService userRegistrationService;

    public UserRegistrationPane(UserRegistrationService userRegistrationService, Observer observer) {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Register");
        title.setBorder(new EmptyBorder(32, 32, 0, 32));
        title.setFont(title.getFont().deriveFont(Font.BOLD, 48));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        JTextField userNameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        JButton cancelButton = new JButton("Cancel");
        JButton registerButton = new JButton("Register");
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
        actionPane.add(registerButton);
        actionPane.add(cancelButton);
        add(title, BorderLayout.NORTH);
        add(credentialsPane);
        add(actionPane, BorderLayout.SOUTH);
        ActionListener authenticationActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getName();
                char[] password = passwordField.getPassword();
                userNameField.setEnabled(false);
                passwordField.setEnabled(false);
                cancelButton.setEnabled(false);
                registerButton.setEnabled(false);
                userRegistrationService.register(userName, password, new UserRegistrationService.Observer() {
                    @Override
                    public void registrationWasSucessful() {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                observer.didRegisterNewUser();
                            }
                        });
                    }

                    @Override
                    public void registrationDidFail() {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(UserRegistrationPane.this, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                                observer.didCancelUserRegistration();
                            }
                        });
                    }
                });
            }
        };
        userNameField.addActionListener(authenticationActionListener);
        passwordField.addActionListener(authenticationActionListener);
        registerButton.addActionListener(authenticationActionListener);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.didCancelUserRegistration();
            }
        });
    }
    
}
