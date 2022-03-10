/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.main;

import java.util.HashMap;
import java.util.Map;
import org.kaizen.navigationController.ViewNavigationModel;
import org.kaizen.navigationController.example.User;

/**
 *
 * @author shane.whitehead
 */
public class MainViewNavigationModel implements ViewNavigationModel<MainViewNavigationModel.View, Map<MainViewNavigationModel.ContextKey, Object>> {
    
    public enum View {
        MAIN, STATS, INVENTORY, SPELLS
    }

    public enum ContextKey {
        USER
    }
    private Map<MainViewNavigationModel.ContextKey, Object> context = new HashMap<>();

    public MainViewNavigationModel(User user) {
        context.put(ContextKey.USER, user);
    }

    @Override
    public void popToRoot() {
        context.remove(ContextKey.USER);
    }

    @Override
    public void applyContextFromView(View view, Object ctx) {
    }

    @Override
    public View getRootView() {
        return View.MAIN;
    }

    @Override
    public Map<MainViewNavigationModel.ContextKey, Object> getContext() {
        return context;
    }
    
}
