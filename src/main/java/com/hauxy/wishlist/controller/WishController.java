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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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



    @GetMapping("/{wishListId}/add")
    public String showAddWishForm(@PathVariable int wishListId, Model model) {
        if (user == null) return "login";
//        if (user == null) return "wishlistGuest";
        WishList wishList = wishListService.getWishListById(wishListId);
        if (user.getUser_id() != wishList.getUserId()) {
            return "wishlistGuest";
        }

        model.addAttribute("wish", new Wish());
        model.addAttribute("wishListId", wishListId);
        return "createNewWish";
    }

    @PostMapping("/{wishlistId}/wish/save")
    public String addNewWish(@ModelAttribute Wish wish, @PathVariable int wishlistId, Model model) {


        if (wish.getWish_name() == null || wish.getWish_name().trim().isEmpty()) {
            model.addAttribute("error", "Wish must have a name");
            model.addAttribute("wish", wish);
            return "createNewWish";
        }

        WishList wishList = wishListService.getWishListById(wishlistId);
        if (user.getUser_id() != wishList.getUserId()) {
            return "wishlistGuest";
        }
        wishService.createNewWish(wishList, wish);
        return "redirect:/wishlist/" + wishlistId;
    }



    @GetMapping("/{wishlistId}/item/{itemId}/delete")
    public String deleteWishFromWishlist(@PathVariable int wishlistId,
                                         @PathVariable int itemId,
                                         Model model, HttpSession session) {

        String result = "";
        WishList wishList = wishListService.getWishListById(wishlistId);
        if (user.getUser_id() != wishList.getUserId()) {
            return "wishlistGuest";
        }
        Wish wish = wishService.getWishById(itemId);

        User user = (User) session.getAttribute("loggedInUser");
        if (user != null && user.getUser_id() == wishList.getUserId()) {

            try {
                wishListService.deleteWishFromWishlist(wishList, wish);
                wishList.deleteWish(wish);
                result = "Success";
            } catch (Exception e) {
                result = "Failed";
            }
        }

        model.addAttribute("result", result);

        return "redirect:/wishlist/" + wishlistId;
    }

    @GetMapping("/{wishlistId}/item/{itemId}/edit")
    public String editWishFromWishlist(@PathVariable int wishlistId, @PathVariable int itemId, Model model, HttpSession session) {
        WishList wishList = wishListService.getWishListById(wishlistId);
        if (user.getUser_id() != wishList.getUserId()) {
            return "wishlistGuest";
        }
        Wish wish = wishService.getWishById(itemId);
        model.addAttribute("wish", wish);
        model.addAttribute("wishlist", wishList);
        return "wishEdit";
    }


    @PostMapping("/{wishlistId}/{wishId}/update")
    public String updateEditedWish(@PathVariable int wishlistId, @PathVariable int wishId, @ModelAttribute Wish wish, HttpSession session) {
        WishList wishList = wishListService.getWishListById(wishlistId);
        if (user.getUser_id() != wishList.getUserId()) {
            return "wishlistGuest";
        }
        wishService.updateWish(wish, wishId);
        return "redirect:/wishlist/" + wishlistId;
    }


    @PostMapping("/{wishlistId}/item/{itemId}/reserve")
    public String reserveItemInWishlist(@PathVariable int wishlistId,
                                        @PathVariable int itemId,
                                        HttpSession session, RedirectAttributes redirectAttributes) {

        Wish wish = wishService.getWishById(itemId);


        if (!wish.getIsReserved()) {
            wishService.reserveWish(itemId);
            redirectAttributes.addFlashAttribute("success", "Item reserved successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "This item is already reserved.");
        }

        return "redirect:/wishlist/" + wishlistId;

    }





}
