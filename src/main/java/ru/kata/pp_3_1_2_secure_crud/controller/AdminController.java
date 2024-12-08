package ru.kata.pp_3_1_2_secure_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.pp_3_1_2_secure_crud.model.Role;
import ru.kata.pp_3_1_2_secure_crud.model.User;
import ru.kata.pp_3_1_2_secure_crud.service.UserService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String showAdminPanel(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin/new")
    public String showNewUserForm(Model model) {
        User user = userService.newUser();
        List<Role> allRoles = userService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);
        return "newUser";
    }

    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        List<Role> allRoles = userService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);
        return "editUser";
    }

    @PostMapping("admin/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("roleId") Integer roleId, @RequestParam(value = "password", required = false) String password) {
        userService.updateUser(user, roleId, password);
        return "redirect:/admin?success";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin?deleted";
    }
}
