package com.tilde.userportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message","Welcome to Tilde User Portal!");
        return "home";  // This resolves to home.html inside /template
    }
}
