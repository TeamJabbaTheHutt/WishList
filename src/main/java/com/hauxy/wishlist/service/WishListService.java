package com.hauxy.wishlist.service;

import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.Repository.repository.WishListRepository;
import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.Wish;
import com.hauxy.wishlist.model.WishList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    private WishListRepository wishListRepository;


    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;

    }

    public User getUserAfterLogin(String email) {
        return wishListRepository.getUserByEmail(email);
    }

    // CRUD

    // CREATE
    public String createNewWishList(WishList wishlist) {
        if (wishListRepository.createNewWishList(wishlist) ==  1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

    // READ
    public List<WishList> getWishLists() {
        return wishListRepository.getWishLists();
    }


    public WishList getWishListById(int id) {

        return wishListRepository.getWishListById(id);
    }
    // UPDATE
    public String updateWishList(WishList newWishList) {
        if (wishListRepository.updateWishList(newWishList) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

    // DELETE
    public String deleteWishListById(int id) {
        if (wishListRepository.deleteWishListById(id) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }


    public List<Wish> getWishesPerWishlist(WishList wishlist) {
        return wishListRepository.getWishesPerWishlist(wishlist);
    }

    public void updateWishListPerWish(WishList wishlist) {
        wishListRepository.updateWishListPerWish(wishlist);
    }

    public void deleteWishFromWishlist(WishList wishlist, Wish wish) {
        wishListRepository.deleteWishFromWishlist(wishlist, wish);
    }

    public String deleteWishlistInWishes_per_wishlist(WishList wishList) {
        if (wishListRepository.deleteWishlistInWishes_per_wishlist(wishList) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

}
