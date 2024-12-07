package ru.kata.pp_3_1_2_secure_crud.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        String role;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            role = ((UserDetails) principal).getAuthorities().iterator().next().getAuthority();
        } else {
            username = principal.toString();
            role = "ROLE_UNAUTHORIZED";
        }
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "index";
    }
}
