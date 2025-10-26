package com.hauxy.wishlist.Repository.repository;


import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.Wish;
import com.hauxy.wishlist.model.WishList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {
    private DAO dao;

    public WishListRepository(DAO dao) {
        this.dao = dao;
    }


    // User
    // CREATE
    public int createNewUser(User newUser) {
        return dao.createNewUser(newUser);
    }

    // READ
    public List<User> getUsers() {
        return dao.getUsers();
    }
    // UPDATE
    public User getUserById(int id) {
        return dao.getUserById(id);
    }
    public int updateUser(User newUser) {
        return dao.updateUser(newUser);
    }

    // DELETE
    public int deleteUserById(int id) {
        return dao.deleteUserById(id);
    }

    // WishList

    // CRUD

    // CREATE
    public int createNewWishList(WishList wishlist) {
        return dao.createNewWishList(wishlist);
    }

    // READ
    public List<WishList> getWishLists() {
        return dao.getWishLists();
    }
    public WishList getWishListById(int id) {
        return dao.getWishListById(id);
    }
    // UPDATE
    public int updateWishList(WishList newWishList) {
        return dao.updateWishList(newWishList);
    }

    // DELETE
    public int deleteWishListById(int id) {
        return dao.deleteUserById(id);
    }

    // Wish
    //CRUD

    // CREATE
    public int createNewWish(Wish newWish) {
        return dao.createNewWish(newWish);
    }
    // READ
    public List<Wish> getWishes() {
        return dao.getWishes();
    }
    public Wish getWishById(int id) {
        return dao.getWishById(id);
    }
    // UPDATE
    public int updateWish(Wish newWish) {
        return dao.updateWish(newWish);

    }
    // DELETE
    public int deleteWishById(int id) {
        return dao.deleteUserById(id);

    }


}
