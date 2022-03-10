/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.launch;

import java.util.HashMap;
import java.util.Map;
import org.kaizen.navigationController.ViewNavigationModel;
import org.kaizen.navigationController.example.User;

// ---------- Login ------------------------------------------------------//

public class LauncViewNavigationModel implements ViewNavigationModel<LauncViewNavigationModel.View, Map<LauncViewNavigationModel.ContextKey, Object>> {

    public enum View {
        CREDENTIALS, REGISTER, MAIN
    }

    public enum ContextKey {
        USER_AUTHENTICATION_SERVICE, USER_REGISTRATION_SERVICE, USER
    }
    private Map<LauncViewNavigationModel.ContextKey, Object> context = new HashMap<>();

    public LauncViewNavigationModel(UserAuthenticationService authenticationService, UserRegistrationService registrationService) {
        context.put(ContextKey.USER_AUTHENTICATION_SERVICE, authenticationService);
        context.put(ContextKey.USER_REGISTRATION_SERVICE, registrationService);
    }

    @Override
    public void popToRoot() {
        context.remove(ContextKey.USER);
    }

    @Override
    public void applyContextFromView(View view, Object ctx) {
        switch (view) {
            case CREDENTIALS:
                if (ctx instanceof User) {
                    context.put(ContextKey.USER, (User) ctx);
                } else {
                    context.remove(ContextKey.USER);
                }
                break;
        }
    }

    @Override
    public View getRootView() {
        return View.CREDENTIALS;
    }

    @Override
    public Map<LauncViewNavigationModel.ContextKey, Object> getContext() {
        return context;
    }
    
}
