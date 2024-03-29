package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDb menuDb;

    @Override
    public void addMenu(MenuModel menu) {
        menuDb.save(menu);
    }

    @Override
    public List<MenuModel> findAllMenuByIdRestoran(Long idRestoran) {
        return menuDb.findByRestoranIdRestoran(idRestoran);
    }

    @Override
    public void deleteMenu(MenuModel menuModel) {
        menuDb.delete(menuModel);
    }

    @Override
    public Optional<MenuModel> getMenuByIdMenu(Long id) {
        return menuDb.findById(id);
    }

    @Override
    public MenuModel changeMenu(MenuModel menuModel) {
        // mengambil object menu yang ingin diubah
        MenuModel targetMenu = menuDb.findById(menuModel.getId()).get();
        targetMenu.setNama(menuModel.getNama());
        targetMenu.setDeskripsi(menuModel.getDeskripsi());
        targetMenu.setHarga(menuModel.getHarga());
        targetMenu.setDurasiMasak(menuModel.getDurasiMasak());
        menuDb.save(targetMenu);
        return targetMenu;
    }
}
