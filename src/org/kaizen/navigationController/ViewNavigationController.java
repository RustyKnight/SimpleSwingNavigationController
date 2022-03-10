/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController;

import javax.swing.JComponent;

/**
 * A concept of a navigation controller
 * @author shane.whitehead
 */
public interface ViewNavigationController<View> {
    /**
     * Represents the component onto which managed views will be presented
     * @return 
     */
    public JComponent getNavigationView();
    
}
