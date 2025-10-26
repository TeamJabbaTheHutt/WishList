package com.hauxy.wishlist.service;

import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.Repository.repository.WishListRepository;
import com.hauxy.wishlist.model.Wish;
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
    public String createNewWish(Wish newWish) {
        if (wishListRepository.createNewWish(newWish) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }
    // READ
    public List<Wish> getWishes() {
        return wishListRepository.getWishes();
    }
    public Wish getWishById(int id) {
        return wishListRepository.getWishById(id);
    }
    // UPDATE
    public String updateWish(Wish newWish) {
        if (wishListRepository.updateWish(newWish) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }
    // DELETE
    public String deleteWishById(int id) {
        if (wishListRepository.deleteUserById(id) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

}
