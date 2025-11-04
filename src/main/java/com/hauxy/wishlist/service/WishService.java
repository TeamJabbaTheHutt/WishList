package com.hauxy.wishlist.service;

import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.Repository.repository.WishListRepository;
import com.hauxy.wishlist.model.Wish;
import com.hauxy.wishlist.model.WishList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private WishListRepository wishListRepository;

    public WishService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    //CRUD

    // CREATE
    public void createNewWish(WishList wishList, Wish newWish) {
        wishListRepository.insertWish(wishList, newWish);
    }
    // READ
    public List<Wish> getWishes() {
        return wishListRepository.getWishes();
    }
    public Wish getWishById(int id) {
        return wishListRepository.getWishById(id);
    }
    // UPDATE
    public String updateWish(Wish newWish, int id) {
        if (wishListRepository.updateWish(newWish, id) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }
    // DELETE
    public String deleteWishById(int id) {
        if (wishListRepository.deleteWishById(id) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

}
