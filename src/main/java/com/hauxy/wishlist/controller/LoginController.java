package com.hauxy.wishlist.controller;

import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.service.LoginService;
import jakarta.servlet.http.HttpSession;
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
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model, HttpSession httpSession) {
        User user = loginService.checkCredentials(email, password);

        if (user != null) {
            model.addAttribute("message", "✅ Login successful");
            model.addAttribute("messageType", "success");
            httpSession.setAttribute("loggedInUser", user);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("message", "❌ Invalid username or password");
            model.addAttribute("messageType", "error");
            return "login";
        }
    }
}
