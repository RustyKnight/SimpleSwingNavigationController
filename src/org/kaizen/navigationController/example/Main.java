/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.kaizen.navigationController.DefaultViewNavigationController;
import org.kaizen.navigationController.example.launch.DefaultUserAuthenticationService;
import org.kaizen.navigationController.example.launch.DefaultUserRegistrationService;
import org.kaizen.navigationController.example.launch.LauncViewNavigationModel;
import org.kaizen.navigationController.example.launch.LaunchViewNavigationFactory;
import org.kaizen.navigationController.example.launch.UserAuthenticationService;
import org.kaizen.navigationController.example.launch.UserRegistrationService;

/**
 *
 * @author shane.whitehead
 */
public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserAuthenticationService authenticationService = new DefaultUserAuthenticationService();
                UserRegistrationService registrationService = new DefaultUserRegistrationService();
                LauncViewNavigationModel model = new LauncViewNavigationModel(authenticationService, registrationService);
                LaunchViewNavigationFactory factory = new LaunchViewNavigationFactory();

                DefaultViewNavigationController<LauncViewNavigationModel.View, Map<LauncViewNavigationModel.ContextKey, Object>> controller = new DefaultViewNavigationController<>(model, factory);

                JPanel contentPane = new JPanel(new BorderLayout()) {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(400, 400);
                    }
                };
                contentPane.add(controller.getNavigationView());

                JFrame frame = new JFrame();
                frame.add(contentPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
