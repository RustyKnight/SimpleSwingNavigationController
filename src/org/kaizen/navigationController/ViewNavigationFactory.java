/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController;

/**
 * A factory for creating the physical view components
 * @author shane.whitehead
 */
public interface ViewNavigationFactory<View, Context> {
    public NavigatableComponentView componentForView(View view, Context ctx);
    
}
