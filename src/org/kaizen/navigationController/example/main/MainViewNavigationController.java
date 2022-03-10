/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.main;

import java.util.Map;
import org.kaizen.navigationController.DefaultViewNavigationController;
import org.kaizen.navigationController.NavigatableComponentView;

public class MainViewNavigationController extends DefaultViewNavigationController<MainViewNavigationModel.View, Map<MainViewNavigationModel.ContextKey, Object>> {

    public interface Observer {
        public void didLogout();
    }
    private Observer observer;

    public MainViewNavigationController(MainViewNavigationModel model, MainViewNavigationFactory viewFactory, Observer observer) {
        super(model, viewFactory);
        this.observer = observer;
    }

    public Observer getObserver() {
        return observer;
    }

    @Override
    protected NavigatableComponentView componentForView(MainViewNavigationModel.View view) {
        NavigatableComponentView navView = super.componentForView(view);
        if (navView instanceof MainNavigatableView) {
            ((MainNavigatableView) navView).setObserver(new MainNavigatableView.Observer() {
                @Override
                public void didLogout() {
                    getObserver().didLogout();
                }
            });
        }
        return navView;
    }

    @Override
    protected void dispose(NavigatableComponentView view) {
        super.dispose(view);
        if (view instanceof MainNavigatableView) {
            ((MainNavigatableView) view).setObserver((MainNavigatableView.Observer) null);
        }
    }
    
}
