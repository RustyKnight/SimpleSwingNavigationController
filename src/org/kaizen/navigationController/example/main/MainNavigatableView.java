/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.main;

import org.kaizen.navigationController.DefaultNavigatableView;

/**
 *
 * @author shane.whitehead
 */
public class MainNavigatableView extends DefaultNavigatableView<MainViewNavigationModel.View> {
    
    public interface Observer {
        public void didLogout();
    }
    private Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void logout() {
        observer.didLogout();
    }
    
}
