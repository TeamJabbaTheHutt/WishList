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
    public String getWishlist(@PathVariable int wishListId, Model model, HttpSession session) {
        this.user = (User) session.getAttribute("loggedInUser");
        WishList wishList = wishListService.getWishListById(wishListId);
        model.addAttribute("wishlist", wishList);
        model.addAttribute("wishes", wishList.getWishes());
        model.addAttribute("user", user);
        if (user == null) return "login";
//        if (user == null) return "wishlistGuest";
        if (user.getUser_id() != wishList.getUserId()) {
            return "wishlistGuest";
        }

        return "wishlistOwner";
    }



    @PostMapping("/{wishlistId}/add")
    public String createWishlistItemForSpecificWishList(@RequestParam int wishListId, Model model, HttpSession session) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);
        model.addAttribute("wishListId", wishListId);
        return "createNewWish";
    }

    @PostMapping("/{wishlistId}/wish/save")
    public String addNewWish(@ModelAttribute Wish wish, @PathVariable int wishlistId, Model model) {
        if (wish.getWish_name() == null || wish.getWish_name().trim().isEmpty()) {
            model.addAttribute("error", "Wish must have a name");
            model.addAttribute("wish", wish);
            return "redirect:/createNewWish";
        }
        WishList wishList = wishListService.getWishListById(wishlistId);
        wishService.createNewWish(wishList, wish);
        return "wishlist/" + wishlistId;
    }


    @DeleteMapping("/{wishlistId}/item/{itemId}/delete")
    public String deleteWishFromWishlist(@PathVariable int wishlistId,
                                         @PathVariable int itemId, Model model, HttpSession session) {
        boolean isOwner = false;
        String result = "";
        WishList wishList = wishListService.getWishListById(wishlistId);
        Wish wish = wishService.getWishById(itemId);

        this.user = (User) session.getAttribute("loggedInUser");
        if (user.getUser_id() == wishList.getUserId()) {
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
        return "redirect:/wishlist/" + wishlistId;
    }

    @PostMapping("/{wishlistId}/item/{itemId}/reserve")
    public String reserveItemInWishlist(@PathVariable int wishlistId,
                                        @PathVariable int itemId,
                                        HttpSession session) {
        boolean isOwner;
        this.user = (User) session.getAttribute("loggedInUser");
        WishList wishList = wishListService.getWishListById(wishlistId);
        String result = "";
        if (user.getUser_id() == wishList.getUserId()) {
            isOwner = true;
        } else {
            isOwner = false;
        }

        return "redirect:/wishlist";
    }





}
