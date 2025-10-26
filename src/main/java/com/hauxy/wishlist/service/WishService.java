package com.hauxy.wishlist.service;

import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.model.Wish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private DAO dao;

    public WishService(DAO dao) {
        this.dao = dao;
    }

    //CRUD

    // CREATE
    public String createNewWish(Wish newWish) {
        if (dao.createNewWish(newWish) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }
    // READ
    public List<Wish> getWishes() {
        return dao.getWishes();
    }
    public Wish getWishById(int id) {
        return dao.getWishById(id);
    }
    // UPDATE
    public String updateWish(Wish newWish) {
        if (dao.updateWish(newWish) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }
    // DELETE
    public String deleteWishById(int id) {
        if (dao.deleteUserById(id) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

}
