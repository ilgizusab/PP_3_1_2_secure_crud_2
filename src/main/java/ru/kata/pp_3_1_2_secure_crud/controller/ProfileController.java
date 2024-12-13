package ru.kata.pp_3_1_2_secure_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.pp_3_1_2_secure_crud.model.User;
import ru.kata.pp_3_1_2_secure_crud.service.UserService;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String showProfileForm(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
}
