package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class RestoranController {
    @Qualifier("restoranServiceImpl")
    @Autowired
    private RestoranService restoranService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/")
    public String home() { return "home"; }

    //URL mapping yang digunakan untuk mengakses halaman add restoran
    @RequestMapping(value = "/restoran/add", method = RequestMethod.GET)
    public String addRestoranFormPage(Model model) {
        RestoranModel newRestoran = new RestoranModel();
        model.addAttribute("restoran", newRestoran);
        return "form-add-restoran";
    }

    //URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add restorana
    @RequestMapping(value = "/restoran/add", method = RequestMethod.POST)
    public String addRestoranSubmit(@ModelAttribute RestoranModel restoran, Model model) {
        restoranService.addRestoran(restoran);
        model.addAttribute("namaResto", restoran.getNama());
        return "add-restoran";
    }

    //URL mapping view
    @RequestMapping(path = "/restoran/view", method = RequestMethod.GET)
    public String view(
            //Request Parameter untuk dipass
            @RequestParam(value = "idRestoran") Long idRestoran, Model model
            ) {

        //Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();

        List<MenuModel> menuList = menuService.findAllMenuByIdRestoran(restoran.getIdRestoran());
        restoran.setListMenu(menuList);

        //Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        //Return view template
        return "view-restoran";
    }

    //API yang digunakan untuk menuju halaman form change restoran
    @RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.GET)
    public String changeRestoranFormPage(@PathVariable Long idRestoran, Model model) {
        //Mengambil existing data restoran
        RestoranModel existingRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        model.addAttribute("restoran", existingRestoran);
        return "form-change-restoran";
    }

    //API yang digunakan untuk submit form change restoran
    @RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.POST)
    public String changeRestoranFormSubmit(@PathVariable Long idRestoran, @ModelAttribute RestoranModel restoran, Model model) {
        RestoranModel newRestoranData = restoranService.changeRestoran(restoran);
        model.addAttribute("restoran", newRestoranData);

        return "change-restoran";
    }

    // LATIHAN 1
    // URL mapping viewAll
    @RequestMapping("/restoran/viewall")
    public String viewAll(Model model) {
        // Mengambil semua objek RestoranModel yang ada
        List<RestoranModel> listRestoran = restoranService.getRestoranList();

        // Add model restoran ke "resto" untuk direncer
        model.addAttribute("restoList", listRestoran);

        // Return view template
        return "viewall-restoran";
    }

    //LATIHAN 3
    @RequestMapping(value = "/menu/deleteResto/{idRestoran}", method = RequestMethod.GET)
    public String deleteResto(@PathVariable(value = "idRestoran") Long idRestoran, Model model) {
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        List<MenuModel> menu = menuService.findAllMenuByIdRestoran(idRestoran);
        if (menu.size() == 0) {
            restoranService.deleteRestoran(idRestoran);
            model.addAttribute("resto", restoran);
            return "delete-restoran";
        }
        return "cancel-delete-resto";
    }


//    @Autowired
//    private RestoranService restoranService;
//    // URL mapping add
//    @RequestMapping("/restoran/add")
//    public String add(
//            // Request Parameter untuk dipass
//            @RequestParam(value = "idRestoran", required = true) String idRestoran,
//            @RequestParam(value = "nama", required = true) String nama,
//            @RequestParam(value = "alamat", required = true) String alamat,
//            @RequestParam(value = "nomorTelepon", required = true) Integer nomorTelepon,
//            Model model
//            ) {
//        // Membuat objek RestoranModel
//        RestoranModel restoran = new RestoranModel(idRestoran, nama, alamat, nomorTelepon);
//
//        // Memanggil service addRestoran
//        restoranService.addRestoran(restoran);
//
//        // Add variabel nama restoran ke "namaResto" untuk dirender
//        model.addAttribute("namaResto", nama);
//
//        // Return view template
//        return "add-restoran";
//    }
//
//    // URL mapping view
//    @RequestMapping("/restoran/view")
//    public String view(
//            // Request Parameter untuk dipass
//            @RequestParam(value = "idRestoran") String idRestoran, Model model
//            ) {
//        // Mengambil objek RestoranModel yang dituju
//        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//
//        // Apabila id restoran tidak ada, return halaman error
//        if (restoran == null) {
//            return "cancel-action";
//        }
//
//        // Add model restoran ke "resto" untuk dirender
//        model.addAttribute("resto", restoran);
//
//        // Return view template
//        return "view-restoran";
//    }
//
//    // URL mapping viewAll
//    @RequestMapping("/restoran/viewall")
//    public String viewAll(Model model) {
//        // Mengambil semua objek RestoranModel yang ada
//        List<RestoranModel> listRestoran = restoranService.getRestoranList();
//
//        // Add model restoran ke "resto" untuk direncer
//        model.addAttribute("restoList", listRestoran);
//
//        // Return view template
//        return "viewall-restoran";
//    }
//
//    // Latihan nomor 1
//    @RequestMapping("/restoran/view/id-restoran/{idRestoran}")
//    public String viewPathVariable(
//            // Request Parameter untuk dipass
//            @PathVariable(value = "idRestoran") String idRestoran, Model model
//    ) {
//        // Mengambil objek RestoranModel yang dituju
//        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//
//        // Apabila id restoran tidak ada, return halaman error
//        if (restoran == null) {
//            return "cancel-action";
//        } else {
//            // Add model restoran ke "resto" untuk dirender
//            model.addAttribute("resto", restoran);
//            // Return view template
//            return "view-restoran";
//        }
//    }
//
//    // Latihan nomor 2
//    @RequestMapping("/restoran/update/id-restoran/{idRestoran}/nomor-telepon/{nomorTelepon}")
//    public String update(
//            // Request Parameter untuk dipass
//            @PathVariable(value = "idRestoran") String idRestoran,
//            @PathVariable(value = "nomorTelepon") Integer nomorTelepon,
//            Model model
//    ) {
//        // Mengambil objek RestoranModel yang dituju
//        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//
//        // Apabila id restoran tidak ada, return halaman error
//        if (restoran == null) {
//            return "cancel-action";
//        } else {
//            // Mengganti nomor telepon dari restoran
//            restoran.setNomorTelepon(nomorTelepon);
//            // Add model restoran ke "resto" untuk dirender
//            model.addAttribute("resto", restoran);
//            // Return update template
//            return "update-nomortelepon";
//        }
//    }
//
//    // Latihan nomor 3
//    @RequestMapping("/restoran/delete/id/{idRestoran}")
//    public String delete(
//            // Request Parameter untuk dipass
//            @PathVariable(value = "idRestoran") String idRestoran,
//            Model model
//    ) {
//        // Mengambil objek RestoranModel yang dituju
//        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//
//        // Apabila id restoran tidak ada, return halaman error
//        if (restoran == null) {
//            return "cancel-action";
//        } else {
//            for (RestoranModel resto : restoranService.getRestoranList()) {
//                if (resto.equals(restoran)) {
//                    restoranService.getRestoranList().remove(resto);
//                    // Add model restoran ke "resto" untuk dirender
//                    model.addAttribute("resto", restoran);
//                    // Return delete template
//                    return "delete-restoran";
//                }
//            }
//        }
//        return null;
//    }
}
