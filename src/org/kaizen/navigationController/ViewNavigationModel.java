/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController;

/**
 * A navigation model
 * @author shane.whitehead
 */
public interface ViewNavigationModel<View, Context> {
    public void popToRoot();
    public void applyContextFromView(View view, Object ctx);
    public View getRootView();
    public Context getContext();
}
