package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RestoranController {
    @Autowired
    private RestoranService restoranService;
    // URL mapping add
    @RequestMapping("/restoran/add")
    public String add(
            // Request Parameter untuk dipass
            @RequestParam(value = "idRestoran", required = true) String idRestoran,
            @RequestParam(value = "nama", required = true) String nama,
            @RequestParam(value = "alamat", required = true) String alamat,
            @RequestParam(value = "nomorTelepon", required = true) Integer nomorTelepon,
            Model model
            ) {
        // Membuat objek RestoranModel
        RestoranModel restoran = new RestoranModel(idRestoran, nama, alamat, nomorTelepon);

        // Memanggil service addRestoran
        restoranService.addRestoran(restoran);

        // Add variabel nama restoran ke "namaResto" untuk dirender
        model.addAttribute("namaResto", nama);

        // Return view template
        return "add-restoran";
    }

    // URL mapping view
    @RequestMapping("/restoran/view")
    public String view(
            // Request Parameter untuk dipass
            @RequestParam(value = "idRestoran") String idRestoran, Model model
            ) {
        // Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        // Apabila id restoran tidak ada, return halaman error
        if (restoran == null) {
            return "cancel-action";
        }

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        // Return view template
        return "view-restoran";
    }

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

    // Latihan nomor 1
    @RequestMapping("/restoran/view/id-restoran/{idRestoran}")
    public String viewPathVariable(
            // Request Parameter untuk dipass
            @PathVariable(value = "idRestoran") String idRestoran, Model model
    ) {
        // Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        // Apabila id restoran tidak ada, return halaman error
        if (restoran == null) {
            return "cancel-action";
        } else {
            // Add model restoran ke "resto" untuk dirender
            model.addAttribute("resto", restoran);
            // Return view template
            return "view-restoran";
        }
    }

    // Latihan nomor 2
    @RequestMapping("/restoran/update/id-restoran/{idRestoran}/nomor-telepon/{nomorTelepon}")
    public String update(
            // Request Parameter untuk dipass
            @PathVariable(value = "idRestoran") String idRestoran,
            @PathVariable(value = "nomorTelepon") Integer nomorTelepon,
            Model model
    ) {
        // Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        // Apabila id restoran tidak ada, return halaman error
        if (restoran == null) {
            return "cancel-action";
        } else {
            // Mengganti nomor telepon dari restoran
            restoran.setNomorTelepon(nomorTelepon);
            // Add model restoran ke "resto" untuk dirender
            model.addAttribute("resto", restoran);
            // Return update template
            return "update-nomortelepon";
        }
    }

    // Latihan nomor 3
    @RequestMapping("/restoran/delete/id/{idRestoran}")
    public String delete(
            // Request Parameter untuk dipass
            @PathVariable(value = "idRestoran") String idRestoran,
            Model model
    ) {
        // Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        // Apabila id restoran tidak ada, return halaman error
        if (restoran == null) {
            return "cancel-action";
        } else {
            for (RestoranModel resto : restoranService.getRestoranList()) {
                if (resto.equals(restoran)) {
                    restoranService.getRestoranList().remove(resto);
                    // Add model restoran ke "resto" untuk dirender
                    model.addAttribute("resto", restoran);
                    // Return delete template
                    return "delete-restoran";
                }
            }
        }
        return null;
    }
}
