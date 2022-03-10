/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController;

import javax.swing.JComponent;

/**
 * Represents a link between the physical view and the navigation workflow
 * A view may generate any number of events, this provides a way to encapsulate
 * those events into a navigation workflow without the view having to phsyically
 * implement any other requirements
 * @author shane.whitehead
 */
public interface NavigatableComponentView<View> {
    public static interface Observer<View> {
        public void pop();
        public void push(View view);
        public void popToRoot();
        public void replaceWith(View view);
        public void applyContextFromView(View view, Object ctx);
    }

    public JComponent getComponent();
    public void setObserver(Observer observer);   
}
