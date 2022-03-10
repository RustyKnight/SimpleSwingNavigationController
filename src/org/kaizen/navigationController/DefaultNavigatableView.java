/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController;

import javax.swing.JComponent;

/**
 *
 * @author shane.whitehead
 */
public class DefaultNavigatableView<View> implements NavigatableComponentView<View> {
    private JComponent component;
    private Observer<View> observer;

    public void setComponent(JComponent component) {
        this.component = component;
    }

    @Override
    public JComponent getComponent() {
        return component;
    }

    @Override
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void pop() {
        observer.pop();
    }

    public void push(View view) {
        observer.push(view);
    }

    public void popToRoot() {
        observer.popToRoot();
    }

    public void replaceWith(View view) {
        observer.replaceWith(view);
    }

    public void applyContextFromView(View view, Object ctx) {
        observer.applyContextFromView(view, ctx);
    }
    
}
