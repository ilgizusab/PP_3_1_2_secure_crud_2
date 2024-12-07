package ru.kata.pp_3_1_2_secure_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Возвращает имя шаблона Thymeleaf (без расширения .html)
    }
}
