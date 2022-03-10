/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.kaizen.navigationController.example.User;

/**
 *
 * @author shane.whitehead
 */
public class MainCharacterPane extends JPanel {
    
    public interface Observer {
        public void didLogout();

        public void presentStats();

        public void presentInventory();

        public void presentSpells();
    }
    private Observer observer;
    private User user;

    public MainCharacterPane(User user, Observer observer) {
        setLayout(new BorderLayout());
        JLabel mainTitleLabel = new JLabel("Welcome");
        mainTitleLabel.setFont(mainTitleLabel.getFont().deriveFont(Font.BOLD, 48));
        mainTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainTitleLabel.setVerticalAlignment(JLabel.CENTER);
        JLabel subTitleLabel = new JLabel(user.getName());
        subTitleLabel.setFont(mainTitleLabel.getFont().deriveFont(Font.BOLD, 24));
        subTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        subTitleLabel.setVerticalAlignment(JLabel.CENTER);
        JPanel titlePane = new JPanel(new GridBagLayout());
        titlePane.setBorder(new EmptyBorder(32, 32, 4, 32));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(4, 4, 4, 4);
        titlePane.add(mainTitleLabel, gbc);
        titlePane.add(subTitleLabel, gbc);
        add(titlePane, BorderLayout.NORTH);
        JButton statusButton = new JButton("Stats");
        JButton inventoryButton = new JButton("Inventory");
        JButton spellsButton = new JButton("Spells");
        JButton logoutButton = new JButton("Logout");
        JPanel actionPane = new JPanel(new GridBagLayout());
        actionPane.setBorder(new EmptyBorder(4, 32, 32, 32));
        actionPane.add(statusButton, gbc);
        actionPane.add(inventoryButton, gbc);
        actionPane.add(spellsButton, gbc);
        gbc.insets = new Insets(32, 0, 0, 0);
        actionPane.add(logoutButton, gbc);
        add(actionPane);
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.presentStats();
            }
        });
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.presentInventory();
            }
        });
        spellsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.presentSpells();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.didLogout();
            }
        });
    }
    
}
