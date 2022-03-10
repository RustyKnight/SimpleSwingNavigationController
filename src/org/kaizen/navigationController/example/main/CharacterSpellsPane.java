/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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
public class CharacterSpellsPane extends JPanel {
    
    public interface Observer {
        public void presentMain();

        public void presentStats();

        public void presentInventory();
    }
    private Observer observer;
    private User user;

    public CharacterSpellsPane(User user, Observer observer) {
        setLayout(new BorderLayout());
        JLabel mainTitleLabel = new JLabel("Spells");
        mainTitleLabel.setFont(mainTitleLabel.getFont().deriveFont(Font.BOLD, 48));
        mainTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainTitleLabel.setVerticalAlignment(JLabel.CENTER);
        JPanel titlePane = new JPanel();
        titlePane.setBorder(new EmptyBorder(32, 32, 4, 32));
        titlePane.add(mainTitleLabel);
        add(titlePane, BorderLayout.NORTH);
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.add(new JLabel("All your spell is belong to us"));
        add(contentPane);
        JButton mainButton = new JButton("Main");
        JButton statusButton = new JButton("Stats");
        JButton inventoryButton = new JButton("Inventory");
        JPanel actionPane = new JPanel();
        actionPane.setBorder(new EmptyBorder(4, 32, 32, 32));
        actionPane.add(mainButton);
        actionPane.add(statusButton);
        actionPane.add(inventoryButton);
        add(actionPane, BorderLayout.SOUTH);
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.presentMain();
            }
        });
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
    }
    
}
