package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;

import java.util.List;

public interface MenuRestService {
    MenuModel createMenu(MenuModel menu);
    List<MenuModel> retriveListMenu();
    MenuModel getMenuById(Long id);
    MenuModel changeMenu(Long id, MenuModel menu);
    void deleteMenu(Long id);
}
