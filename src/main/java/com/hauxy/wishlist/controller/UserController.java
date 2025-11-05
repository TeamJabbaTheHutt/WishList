package com.hauxy.wishlist.controller;

import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String userName, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession httpSession) {

        if (userService.checkIfUserExist(email)) {
            model.addAttribute("username",userName);
            model.addAttribute("email",email);
            model.addAttribute("password",password);
            User user = new User(email,userName, password);

            if (userService.createNewUser(user).equals("Success")) {
                httpSession.setAttribute("loggedInUser", user);
                return "redirect:/dashboard";
            } else {
                model.addAttribute("message", "❌ Failed to create user, try again");
                model.addAttribute("messageType", "error");
                return "register";
            }

        } else {
            model.addAttribute("message", "❌ A user with this email already exist");
            model.addAttribute("messageType", "error");
            return "register";
        }

    }

}