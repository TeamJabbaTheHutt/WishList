package com.hauxy.wishlist.service;

import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.model.WishList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    private DAO dao;

    public WishListService(DAO dao) {
        this.dao = dao;
    }

    // CRUD

    // CREATE
    public String createNewWishList(WishList wishlist) {
        if (dao.createNewWishList(wishlist) ==  1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

    // READ
    public List<WishList> getWishLists() {
        return dao.getWishLists();
    }
    public WishList getWishListById(int id) {
        return dao.getWishListById(id);
    }
    // UPDATE
    public String updateWishList(WishList newWishList) {
        if (dao.updateWishList(newWishList) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

    // DELETE
    public String deleteWishListById(int id) {
        if (dao.deleteUserById(id) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }


}
