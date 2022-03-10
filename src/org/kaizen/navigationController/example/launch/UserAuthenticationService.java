/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.launch;

import org.kaizen.navigationController.example.User;

/**
 *
 * @author shane.whitehead
 */
public interface UserAuthenticationService {
    public static interface Observer {
        public void authenticationWasSucessful(User user);
        public void authenticationDidFail();
    }

    public void authenticate(String userName, char[] password, Observer observer);
}
