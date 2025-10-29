package com.hauxy.wishlist.controller;

import com.hauxy.wishlist.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping()
    public String login(Model model) {
        model.addAttribute("email", email);
        model.addAttribute("password", password);
    }
}
