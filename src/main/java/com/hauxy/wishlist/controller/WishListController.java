package com.hauxy.wishlist.controller;

import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.WishList;
import com.hauxy.wishlist.service.WishListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/dashboard")
@Controller
public class WishListController {

    @Autowired
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

        List<WishList> wishList = wishListService.getWishListsForUser(user.getUser_id());

        model.addAttribute("user", user);
        model.addAttribute("wishlists", wishList != null ? wishList : List.of());
        return "dashboard";
    }

    @PostMapping("/create")
    public String createWishlist(@RequestParam("wishlistName") String wishlistName, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        WishList newWishList = new WishList();
        newWishList.setWishListName(wishlistName);
        newWishList.setUserId(user.getUser_id());

        String result = wishListService.createNewWishList(newWishList);

        if ("Success".equals(result)) {
            return "redirect:/dashboard";
        } else {
            return "redirect:/dashboard?error=creationFailed";
        }
    }
}
