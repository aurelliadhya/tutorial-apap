package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class MenuRestServiceImpl implements MenuRestService {
    @Autowired
    MenuDb menuDB;

    @Override
    public MenuModel createMenu(MenuModel menu) {
        return menuDB.save(menu);
    }

    @Override
    public List<MenuModel> retriveListMenu() {return menuDB.findAll();}

    @Override
    public MenuModel getMenuById(Long id) {
        Optional<MenuModel> menu = menuDB.findById(id);
        if(menu.isPresent()) {
            return menu.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public MenuModel changeMenu(Long id, MenuModel menuUpdated) {
        MenuModel menu = getMenuById(id);
        menu.setNama(menuUpdated.getNama());
        menu.setHarga(menuUpdated.getHarga());
        menu.setDurasiMasak(menuUpdated.getDurasiMasak());
        menu.setDeskripsi(menuUpdated.getDeskripsi());
        return menuDB.save((menu));
    }

    @Override
    public void deleteMenu(Long id) {
        MenuModel menu = getMenuById(id);
        menuDB.delete(menu);
    }
}
