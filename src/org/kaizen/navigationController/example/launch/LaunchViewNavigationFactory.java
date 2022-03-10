/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.launch;

import java.util.Map;
import org.kaizen.navigationController.DefaultNavigatableView;
import org.kaizen.navigationController.NavigatableComponentView;
import org.kaizen.navigationController.ViewNavigationFactory;
import org.kaizen.navigationController.example.main.MainViewNavigationFactory;
import org.kaizen.navigationController.example.main.MainViewNavigationController;
import org.kaizen.navigationController.example.main.MainViewNavigationModel;
import org.kaizen.navigationController.example.User;

/**
 *
 * @author shane.whitehead
 */
public class LaunchViewNavigationFactory implements ViewNavigationFactory<LauncViewNavigationModel.View, Map<LauncViewNavigationModel.ContextKey, Object>> {
    @Override
    public NavigatableComponentView componentForView(LauncViewNavigationModel.View view, Map<LauncViewNavigationModel.ContextKey, Object> ctx) {
        switch (view) {
            case CREDENTIALS:
                {
                    Object value = ctx.get(LauncViewNavigationModel.ContextKey.USER_AUTHENTICATION_SERVICE);
                    if (!(value instanceof UserAuthenticationService)) {
                        throw new RuntimeException("UserAuthenticator is unspecified");
                    }
                    DefaultNavigatableView<LauncViewNavigationModel.View> navigatableView = new DefaultNavigatableView<>();
                    UserAuthenticationService userAuthenticator = (UserAuthenticationService) value;
                    LoginCredentialsPane pane = new LoginCredentialsPane(userAuthenticator, new LoginCredentialsPane.Observer() {
                        @Override
                        public void authenticationWasSuccessful(User user) {
                            navigatableView.applyContextFromView(LauncViewNavigationModel.View.CREDENTIALS, user);
                            navigatableView.push(LauncViewNavigationModel.View.MAIN);
                        }

                        @Override
                        public void performUserRegistration() {
                            navigatableView.push(LauncViewNavigationModel.View.REGISTER);
                        }
                    });
                    navigatableView.setComponent(pane);
                    return navigatableView;
                }
            case REGISTER:
                {
                    // This could quite easily be a sub navigation workflow!!
                    Object value = ctx.get(LauncViewNavigationModel.ContextKey.USER_REGISTRATION_SERVICE);
                    if (!(value instanceof UserRegistrationService)) {
                        throw new RuntimeException("UserRegistrationService is unspecified");
                    }
                    DefaultNavigatableView<LauncViewNavigationModel.View> navigatableView = new DefaultNavigatableView<>();
                    UserRegistrationService service = (UserRegistrationService) value;
                    UserRegistrationPane pane = new UserRegistrationPane(service, new UserRegistrationPane.Observer() {
                        @Override
                        public void didRegisterNewUser() {
                            // We could "replace" the current view with
                            // Login instead
                            navigatableView.pop();
                        }

                        @Override
                        public void didCancelUserRegistration() {
                            navigatableView.pop();
                        }
                    });
                    navigatableView.setComponent(pane);
                    return navigatableView;
                }
            case MAIN:
                {
                    Object value = ctx.get(LauncViewNavigationModel.ContextKey.USER);
                    if (!(value instanceof User)) {
                        throw new RuntimeException("Can not present main view as User is undefined");
                    }
                    User user = (User) value;
                    MainViewNavigationModel model = new MainViewNavigationModel(user);
                    MainViewNavigationFactory factory = new MainViewNavigationFactory();
                    DefaultNavigatableView<LauncViewNavigationModel.View> navigatableView = new DefaultNavigatableView<>();
                    MainViewNavigationController controller = new MainViewNavigationController(model, factory, new MainViewNavigationController.Observer() {
                        @Override
                        public void didLogout() {
                            navigatableView.popToRoot();
                        }
                    });
                    navigatableView.setComponent(controller.getNavigationView());
                    return navigatableView;
                }
        }
        throw new RuntimeException("Invalid view: " + view);
    }
    
}
