package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    void addMenu(MenuModel menu);
    List<MenuModel> findAllMenuByIdRestoran(Long idRestoran);

    // Method untuk mendapatkan data sebuah menu berdasarkan id restoran
    Optional<MenuModel> getMenuByIdMenu(Long id);

    void deleteMenu(MenuModel menuModel);

    MenuModel changeMenu(MenuModel menuModel);
}
