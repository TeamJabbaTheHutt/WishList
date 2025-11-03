package com.hauxy.wishlist.controller;

import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            model.addAttribute("error", "All fields are required");
            return "register";
        }

        boolean emailExists = false;


        for (User existingUser : userService.getUsers()) {
            if (existingUser.getEmail().equalsIgnoreCase(user.getEmail())) {
                emailExists = true;
                break;
            }
        }

        if (emailExists) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }


        String result = userService.createNewUser(user);

        if (result.equals("Success")) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "Something went wrong. Please try again.");
            return "register";
        }
    }

}