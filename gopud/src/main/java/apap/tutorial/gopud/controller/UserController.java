package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.service.RoleService;
import apap.tutorial.gopud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        String username = user.getUsername();
        if (userService.getUserByUsername(username) != null) {
            String message = "Username telah terdaftar";
            model.addAttribute("message", message);
            return "home";
        }

        String password = user.getPassword();

        Boolean containtsNumber = false;
        Boolean containtsLetter = false;

        if (password.length() < 10) {
            return "invalid-password";
        } else {
            for (int i=0;i<password.length();i++) {
                if (Character.isDigit(password.charAt(i))) {
                    containtsNumber = true;
                } if (Character.isAlphabetic(password.charAt(i))) {
                    containtsLetter = true;
                }
            }
        }

        if (!(containtsNumber = true) || !(containtsLetter = true)) {
            return "invalid-password";
        }

        userService.addUser(user);
        return "home";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    private String updatePasswordSubmit(@ModelAttribute UserModel user, String newPassword, String oldPassword, String confirmPassword, Model model) {
        UserModel existingUser = userService.getUserByUsername(user.getUsername());

        model.addAttribute("listRole", roleService.findAll());

        if (!userService.isMatching(oldPassword, existingUser.getPassword())) {
            return "update-password-failed";
        } if (!newPassword.equals(confirmPassword)) {
            return "update-password-failed";
        }

        existingUser.setPassword(newPassword);
        userService.addUser(existingUser);
        return "update-password-success";
    }
}
