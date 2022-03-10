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
public class CharacterStatsPane extends JPanel {
    
    public interface Observer {
        public void presentMain();

        public void presentSpells();

        public void presentInventory();
    }
    private Observer observer;
    private User user;

    public CharacterStatsPane(User user, Observer observer) {
        setLayout(new BorderLayout());
        JLabel mainTitleLabel = new JLabel("Stats");
        mainTitleLabel.setFont(mainTitleLabel.getFont().deriveFont(Font.BOLD, 48));
        mainTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainTitleLabel.setVerticalAlignment(JLabel.CENTER);
        JPanel titlePane = new JPanel();
        titlePane.setBorder(new EmptyBorder(32, 32, 4, 32));
        titlePane.add(mainTitleLabel);
        add(titlePane, BorderLayout.NORTH);
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.add(new JLabel("All your status is belong to us"));
        add(contentPane);
        JButton mainButton = new JButton("Main");
        JButton spellsButton = new JButton("Spells");
        JButton inventoryButton = new JButton("Inventory");
        JPanel actionPane = new JPanel();
        actionPane.setBorder(new EmptyBorder(4, 32, 32, 32));
        actionPane.add(mainButton);
        actionPane.add(spellsButton);
        actionPane.add(inventoryButton);
        add(actionPane, BorderLayout.SOUTH);
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.presentMain();
            }
        });
        spellsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.presentSpells();
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
