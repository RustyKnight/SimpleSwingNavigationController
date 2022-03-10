/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController;

/**
 * Allows interested views to be notified when their state changes
 * @author shane.whitehead
 */
public interface NavigatableView {
    public void willDismissView();
    public void didDismissView();
    public void willPresentView();
    public void didPresentView();   
}
