package apap.tutorial.gopud.restcontroller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.service.MenuRestService;
import apap.tutorial.gopud.service.RestoranRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class MenuRestController {
    @Autowired
    MenuRestService menuRestService;

    @Autowired
    RestoranRestService restoranRestService;

    @PostMapping(value = "/menu")
    private MenuModel createMenu(@Valid @RequestBody MenuModel menu, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        return menuRestService.createMenu(menu);
    }


    @PutMapping(value = "/menu/{menuId}")
    private ResponseEntity<String> updateMenu(@PathVariable(value = "menuId") Long id, @RequestBody MenuModel menu) {
        try {
            menuRestService.changeMenu(id, menu);
            return ResponseEntity.ok("Menu update success!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Menu" + String.valueOf(menu) + " Not Found");
        }
    }

    @GetMapping(value = "/menu/{menuId}")
    private MenuModel retriveMenu(@PathVariable("menuId") Long id) {
        try {
            return menuRestService.getMenuById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Menu" + String.valueOf(id) + " Not Found");
        }
    }

    @GetMapping(value = "/menus")
    private List<MenuModel> retriveListMenu() {
        return menuRestService.retriveListMenu();
    }

    @DeleteMapping(value = "/menu/{menuId}")
    private ResponseEntity<String> deleteMenu(@PathVariable("menuId") Long id) {
        try {
            menuRestService.deleteMenu(id);
            return ResponseEntity.ok("Menu with ID " + String.valueOf(id) + " deleted!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Menu" + String.valueOf(id) + " Not Found");
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request still has menu, please delete the menu first!");
        }
    }
}