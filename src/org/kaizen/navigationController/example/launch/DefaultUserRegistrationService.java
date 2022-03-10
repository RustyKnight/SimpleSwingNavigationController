/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.launch;

import org.kaizen.navigationController.example.launch.UserRegistrationService;
import java.util.Random;

/**
 *
 * @author shane.whitehead
 */
public class DefaultUserRegistrationService implements UserRegistrationService {
    private Random rnd = new Random();

    @Override
    public void register(String userName, char[] password, Observer observer) {
        // Simulate some external service
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (rnd.nextBoolean()) {
                    observer.registrationWasSucessful();
                } else {
                    observer.registrationDidFail();
                }
            }
        }).start();
    }

}
