package com.hauxy.wishlist.controller;

import com.hauxy.wishlist.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/login")
@Controller
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping()
    public String showLogin() {
        return "login";
    }

    @PostMapping()
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model) {

        if (loginService.checkCredentials(email, password)) {
            model.addAttribute("message", "✅ Login successful");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "❌ Invalid username or password");
            model.addAttribute("messageType", "error");
        }

        return "wishlist/{email}";
    }
}
