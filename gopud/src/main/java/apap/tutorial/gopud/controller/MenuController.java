package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;

    @Qualifier("restoranServiceImpl")
    @Autowired
    RestoranService restoranService;

    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.GET)
    private String addProductFormPage(@PathVariable(value = "idRestoran") Long idRestoran, Model model) {
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        ArrayList<MenuModel> listMenu = new ArrayList<>();
        restoran.setListMenu(listMenu);
        listMenu.add(new MenuModel());
        model.addAttribute("restoran", restoran);
        model.addAttribute("nama", "Add Menu");

        return "form-add-menu";
    }

    @RequestMapping(value = "menu/add/{idRestoran}", params = {"save"}, method = RequestMethod.POST)
    private String addProductSubmit(@ModelAttribute RestoranModel restoran, Model model) {
        RestoranModel existingRestoran = restoranService.getRestoranByIdRestoran(restoran.getIdRestoran()).get();
        for (MenuModel menu : restoran.getListMenu()) {
            menu.setRestoran(existingRestoran);
            menuService.addMenu(menu);
        }
        model.addAttribute("nama", "baru");

        return "add-menu";
    }

    //LATIHAN 2
    //API yang digunakan untuk menuju halaman form change menu
    @RequestMapping(value = "menu/changeMenu/{id}", method = RequestMethod.GET)
    public String changeMenuFormPage(@PathVariable Long id, Model model) {
        //Mengambil existing data menu
        MenuModel existingMenu = menuService.getMenuByIdMenu(id).get();
        model.addAttribute("menu", existingMenu);
        return "form-change-menu";
    }

    //API yang digunakan untuk submit form change menu
    @RequestMapping(value = "menu/changeMenu/{id}", method = RequestMethod.POST)
    public String changeMenuFormSubmit(@PathVariable Long id, @ModelAttribute MenuModel menu, Model model) {
        MenuModel newMenuData = menuService.changeMenu(menu);
        return "change-menu";
    }

    //LATIHAN 4
    @RequestMapping(value = "/menu/deleteMenu", method = RequestMethod.POST)
    public String deleteMenu(@ModelAttribute RestoranModel restoran, Model model) {
        for (MenuModel menu : restoran.getListMenu()) {
            menuService.deleteMenu(menu);
        }
        return "delete-menu";
    }

    @RequestMapping(value = "/menu/add/{idRestoran}", params = {"addRow"}, method = RequestMethod.POST)
    public String addRow(RestoranModel restoran, MenuModel menu, BindingResult bindingResult, Model model) {
        if (restoran.getListMenu() == null) {
            restoran.setListMenu(new ArrayList<MenuModel>());
        }
        restoran.getListMenu().add(menu);
        model.addAttribute("restoran", restoran);
        return "form-add-menu";
    }

    @RequestMapping(value = "/menu/add/{idRestoran}", params = {"removeRow"}, method = RequestMethod.POST)
    public String removeRow(RestoranModel restoran, BindingResult bindingResult, HttpServletRequest req, Model model) {
        Integer id = Integer.valueOf(req.getParameter("removeRow"));
        restoran.getListMenu().remove(id.intValue());
        model.addAttribute("restoran", restoran);
        return "form-add-menu";
    }
}
