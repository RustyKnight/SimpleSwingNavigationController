/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.launch;

/**
 *
 * @author shane.whitehead
 */
public interface UserRegistrationService {
    public static interface Observer {
        public void registrationWasSucessful();
        public void registrationDidFail();
    }

    public void register(String userName, char[] password, Observer observer);
}
