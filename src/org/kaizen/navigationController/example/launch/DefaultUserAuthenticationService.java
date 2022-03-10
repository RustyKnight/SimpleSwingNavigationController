/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.launch;

import org.kaizen.navigationController.example.launch.UserAuthenticationService;
import java.util.Random;
import org.kaizen.navigationController.example.DefaultUser;

/**
 *
 * @author shane.whitehead
 */
public class DefaultUserAuthenticationService implements UserAuthenticationService {
    private Random rnd = new Random();

    @Override
    public void authenticate(String userName, char[] password, Observer observer) {
        // Simulate some external service
        new Thread(new Runnable() {
            @Override
            public void run() {
//                if (rnd.nextBoolean()) {
                    observer.authenticationWasSucessful(new DefaultUser(userName));
//                } else {
//                    observer.authenticationDidFail();
//                }
            }
        }).start();
    }
    
}
