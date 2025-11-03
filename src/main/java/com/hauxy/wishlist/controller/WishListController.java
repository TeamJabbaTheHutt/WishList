package com.hauxy.wishlist.controller;

import com.hauxy.wishlist.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WishListController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "dashboard";
    }
}
