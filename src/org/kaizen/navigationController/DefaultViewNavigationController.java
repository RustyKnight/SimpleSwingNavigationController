/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * A default implementation of the ViewNavigationController
 * @author shane.whitehead
 */
public class DefaultViewNavigationController<View, Context> implements ViewNavigationController<View> {
    
    protected class ViewModel {
        private View view;
        private NavigatableComponentView navigatableView;

        public ViewModel(View view, NavigatableComponentView component) {
            this.view = view;
            this.navigatableView = component;
        }

        public NavigatableComponentView getNavigatableView() {
            return navigatableView;
        }

        public View getView() {
            return view;
        }
    }
    private JComponent navigationView;
    private FILOStack<ViewModel> viewStack = new FILOStack<ViewModel>();
    private ViewNavigationModel<View, Context> model;
    private ViewNavigationFactory<View, Context> viewFactory;

    public DefaultViewNavigationController(JComponent navigationView, ViewNavigationModel<View, Context> model, ViewNavigationFactory<View, Context> viewFactory) {
        this.navigationView = navigationView;
        this.model = model;
        this.viewFactory = viewFactory;
        push(model.getRootView());
    }

    public DefaultViewNavigationController(ViewNavigationModel<View, Context> model, ViewNavigationFactory<View, Context> viewFactory) {
        this(new JPanel(new BorderLayout()), model, viewFactory);
    }

    @Override
    public JComponent getNavigationView() {
        return navigationView;
    }

    protected FILOStack<ViewModel> getViewStack() {
        return viewStack;
    }

    public ViewNavigationFactory<View, Context> getViewFactory() {
        return viewFactory;
    }

    public ViewNavigationModel<View, Context> getModel() {
        return model;
    }

    protected NavigatableView navigatableViewFromModel(ViewModel model) {
        if (model == null) {
            return null;
        }
        JComponent viewComponent = model.getNavigatableView().getComponent();
        if (!(viewComponent instanceof NavigatableView)) {
            return null;
        }
        return (NavigatableView) viewComponent;
    }

    protected void willDismiss(ViewModel model) {
        NavigatableView viewComponent = navigatableViewFromModel(model);
        if (viewComponent == null) {
            return;
        }
        viewComponent.willDismissView();
    }

    protected void didDismiss(ViewModel model) {
        NavigatableView viewComponent = navigatableViewFromModel(model);
        if (viewComponent == null) {
            return;
        }
        viewComponent.didDismissView();
    }

    protected void willPresent(ViewModel model) {
        NavigatableView viewComponent = navigatableViewFromModel(model);
        if (viewComponent == null) {
            return;
        }
        viewComponent.willPresentView();
    }

    protected void didPresent(ViewModel model) {
        NavigatableView viewComponent = navigatableViewFromModel(model);
        if (viewComponent == null) {
            return;
        }
        viewComponent.didPresentView();
    }

    protected NavigatableComponentView componentForView(View view) {
        NavigatableComponentView navigatableView = getViewFactory().componentForView(view, getModel().getContext());
        navigatableView.setObserver(new NavigatableComponentView.Observer<View>() {
            @Override
            public void pop() {
                DefaultViewNavigationController.this.pop();
            }

            @Override
            public void push(View view) {
                DefaultViewNavigationController.this.push(view);
            }

            @Override
            public void popToRoot() {
                DefaultViewNavigationController.this.popToRoot();
            }

            @Override
            public void replaceWith(View view) {
                replaceCurrentViewWith(view);
            }

            @Override
            public void applyContextFromView(View view, Object ctx) {
                getModel().applyContextFromView(view, ctx);
            }
        });
        return navigatableView;
    }

    protected void dispose(NavigatableComponentView view) {
        view.setObserver(null);
    }

    protected void push(View view) {
        JComponent navPane = getNavigationView();
        ViewModel currentView = getViewStack().peekLast();
        willDismiss(currentView);
        NavigatableComponentView<View> navigatableView = componentForView(view);
        ViewModel newView = new ViewModel(view, navigatableView);
        getViewStack().push(newView);
        navPane.removeAll();
        didDismiss(currentView);
        willPresent(newView);
        navPane.add(navigatableView.getComponent());
        navPane.revalidate();
        navPane.repaint();
        didPresent(newView);
    }
    //
    //        protected void pushNextViewAfter(View view, Object context) {
    //            getModel().applyContextFromView(view, context);
    //            View nextView = getModel().nextViewAfter(view);
    //            push(nextView);
    //        }

    protected void pop() {
        ViewModel outGoingModel = getViewStack().pop();
        dispose(outGoingModel.getNavigatableView());
        willDismiss(outGoingModel);
        ViewModel nextModel = getViewStack().peekLast();
        JComponent navPane = getNavigationView();
        navPane.removeAll();
        didDismiss(outGoingModel);
        if (nextModel == null) {
            push(getModel().getRootView());
            return;
        }
        willPresent(nextModel);
        navPane.add(nextModel.getNavigatableView().getComponent());
        navPane.revalidate();
        navPane.repaint();
        didPresent(nextModel);
    }

    protected void popToRoot() {
        FILOStack<ViewModel> viewStack = getViewStack();
        ViewModel oldView = viewStack.peekLast();
        willDismiss(oldView);
        getModel().popToRoot();
        ViewModel rootView = viewStack.peekFirst();
        while (viewStack.peekLast() != rootView) {
            ViewModel model = viewStack.pop();
            dispose(model.getNavigatableView());
        }
        JComponent navPane = getNavigationView();
        navPane.removeAll();
        didDismiss(oldView);
        willPresent(rootView);
        navPane.add(rootView.getNavigatableView().getComponent());
        navPane.revalidate();
        navPane.repaint();
        didPresent(rootView);
    }

    protected void replaceCurrentViewWith(View view) {
        ViewModel oldView = getViewStack().peekLast();
        push(view);
        getViewStack().remove(oldView);
    }
    
}
