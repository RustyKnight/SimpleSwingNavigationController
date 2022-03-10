/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kaizen.navigationController.example.main;

import java.util.Map;
import org.kaizen.navigationController.DefaultNavigatableView;
import org.kaizen.navigationController.NavigatableComponentView;
import org.kaizen.navigationController.ViewNavigationFactory;
import org.kaizen.navigationController.example.User;

/**
 *
 * @author shane.whitehead
 */
public class MainViewNavigationFactory implements ViewNavigationFactory<MainViewNavigationModel.View, Map<MainViewNavigationModel.ContextKey, Object>> {
    @Override
    public NavigatableComponentView componentForView(MainViewNavigationModel.View view, Map<MainViewNavigationModel.ContextKey, Object> ctx) {
        switch (view) {
            case MAIN:
                {
                    Object value = ctx.get(MainViewNavigationModel.ContextKey.USER);
                    if (!(value instanceof User)) {
                        throw new RuntimeException("Can not presrnt main character pane without a valid user");
                    }
                    User user = (User) value;
                    MainNavigatableView navigatableView = new MainNavigatableView();
                    MainCharacterPane pane = new MainCharacterPane(user, new MainCharacterPane.Observer() {
                        @Override
                        public void didLogout() {
                            navigatableView.logout();
                        }

                        @Override
                        public void presentStats() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.STATS);
                        }

                        @Override
                        public void presentInventory() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.INVENTORY);
                        }

                        @Override
                        public void presentSpells() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.SPELLS);
                        }
                    });
                    navigatableView.setComponent(pane);
                    return navigatableView;
                }
            case INVENTORY:
                {
                    Object value = ctx.get(MainViewNavigationModel.ContextKey.USER);
                    if (!(value instanceof User)) {
                        throw new RuntimeException("Can not present character status pane without a valid user");
                    }
                    User user = (User) value;
                    DefaultNavigatableView<MainViewNavigationModel.View> navigatableView = new DefaultNavigatableView<>();
                    CharacterInvetoryPane pane = new CharacterInvetoryPane(user, new CharacterInvetoryPane.Observer() {
                        @Override
                        public void presentMain() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.MAIN);
                        }

                        @Override
                        public void presentSpells() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.SPELLS);
                        }

                        @Override
                        public void presentStats() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.STATS);
                        }
                    });
                    navigatableView.setComponent(pane);
                    return navigatableView;
                }
            case SPELLS:
                {
                    Object value = ctx.get(MainViewNavigationModel.ContextKey.USER);
                    if (!(value instanceof User)) {
                        throw new RuntimeException("Can not present character spells pane without a valid user");
                    }
                    User user = (User) value;
                    DefaultNavigatableView<MainViewNavigationModel.View> navigatableView = new DefaultNavigatableView<>();
                    CharacterSpellsPane pane = new CharacterSpellsPane(user, new CharacterSpellsPane.Observer() {
                        @Override
                        public void presentMain() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.MAIN);
                        }

                        @Override
                        public void presentStats() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.STATS);
                        }

                        @Override
                        public void presentInventory() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.INVENTORY);
                        }
                    });
                    navigatableView.setComponent(pane);
                    return navigatableView;
                }
            case STATS:
                {
                    Object value = ctx.get(MainViewNavigationModel.ContextKey.USER);
                    if (!(value instanceof User)) {
                        throw new RuntimeException("Can not present character status pane without a valid user");
                    }
                    User user = (User) value;
                    DefaultNavigatableView<MainViewNavigationModel.View> navigatableView = new DefaultNavigatableView<>();
                    CharacterStatsPane pane = new CharacterStatsPane(user, new CharacterStatsPane.Observer() {
                        @Override
                        public void presentMain() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.MAIN);
                        }

                        @Override
                        public void presentSpells() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.SPELLS);
                        }

                        @Override
                        public void presentInventory() {
                            navigatableView.replaceWith(MainViewNavigationModel.View.INVENTORY);
                        }
                    });
                    navigatableView.setComponent(pane);
                    return navigatableView;
                }
        }
        throw new RuntimeException("Invalid view: " + view);
    }
    
}
