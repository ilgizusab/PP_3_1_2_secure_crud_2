package ru.kata.pp_3_1_2_secure_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}