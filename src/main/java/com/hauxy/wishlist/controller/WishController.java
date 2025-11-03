package com.hauxy.wishlist.controller;

import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.Wish;
import com.hauxy.wishlist.model.WishList;
import com.hauxy.wishlist.service.WishListService;
import com.hauxy.wishlist.service.WishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishController {
    private final WishListService wishListService;
    private final WishService wishService;
    private User user = null;

    public WishController(WishListService wishListService, WishService wishService) {
        this.wishListService = wishListService;
        this.wishService = wishService;

    }
    @GetMapping("/{wishListId}")
    public String getWishlist(@RequestParam int wishListId, Model model, HttpSession session) {
        this.user = (User) session.getAttribute("loggedInUser");
        boolean isOwner;
        WishList wishList = wishListService.getWishListById(wishListId);
        if (user.getUser_id() == wishList.getWichlist_id()) {
            isOwner = true;
        } else {
            isOwner = false;
        }
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("wishlist", wishList);
        return "wishlist";
    }

    @GetMapping("/{userId}")
    public String getWishlists(@RequestParam int userId, HttpSession session) {
        boolean isOwner;
        this.user = (User) session.getAttribute("user");
        if (user.getUser_id() == userId) {
            isOwner = true;
        } else {
            isOwner = false;
        }
        return "wishlist";
    }

    @PostMapping("/{wishlistId}/{items}/")
    public String createWishlistItemForSpecificWishList(@RequestParam int wishlistId, @ModelAttribute List<Wish> items, Model model, HttpSession session) {
        boolean isOwner;
        this.user = (User) session.getAttribute("loggedInUser");
        WishList wishList = wishListService.getWishListById(wishlistId);
        String result = "";
        if (user.getUser_id() == wishList.getUser_id()) {
            isOwner = true;
            for (Wish wish : items) {
                result = wishService.createNewWish(wish);
                wishList.insertNewWish(wish);
                wishListService.updateWishListPerWish(wishList);
                System.out.println(result);
            }
        } else {
            isOwner = false;
            result = "Failed: User is not the owner";
        }
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("result", result);
        return "redirect:/wishlist";
    }


    @DeleteMapping("/{wishlistId}/item/{itemId}")
    public String deleteWishFromWishlist(@PathVariable int wishlistId,
                                         @PathVariable int itemId, Model model, HttpSession session) {
        boolean isOwner = false;
        String result = "";
        WishList wishList = wishListService.getWishListById(wishlistId);
        Wish wish = wishService.getWishById(itemId);

        this.user = (User) session.getAttribute("loggedInUser");
        if (user.getUser_id() == wishList.getUser_id()) {
            isOwner = true;
            try {
                wishListService.deleteWishFromWishlist(wishList, wish);
                wishList.deleteWish(wish);
                result = "Success";
            } catch (Exception e) {
                result = "Failed";
            }
        } else {
            isOwner = false;
        }

        model.addAttribute("isOwser", isOwner);
        model.addAttribute("result", result);
        return "redirect:/wishlist";
    }

    @PostMapping("/{wishlistId}/item/{itemId}/reserve")
    public String reserveItemInWishlist(@PathVariable int wishlistId,
                                        @PathVariable int itemId,
                                        HttpSession session) {
        boolean isOwner;
        this.user = (User) session.getAttribute("loggedInUser");
        WishList wishList = wishListService.getWishListById(wishlistId);
        String result = "";
        if (user.getUser_id() == wishList.getUser_id()) {
            isOwner = true;
        } else {
            isOwner = false;
        }

        return "redirect:/wishlist";
    }
}
