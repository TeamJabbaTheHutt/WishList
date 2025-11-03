package com.hauxy.wishlist.controller;

import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.WishList;
import com.hauxy.wishlist.service.WishListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/dashboard")
@Controller
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping()
    public String dashboard(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        List<WishList> wishLists = wishListService.getWishListsForUser(user.getUser_id());

        model.addAttribute("user", user);
        model.addAttribute("wishlists", wishLists != null ? wishLists : List.of());
        return "dashboard";
    }

    @PostMapping("/delete/{wishListId}")
    public String deleteWishList(@PathVariable("wishListId") int wishListId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("loggedInUser");

        if (user != null) {
            wishListService.deleteWishListById(wishListId);
        }

        return "redirect:/dashboard";
    }


}
