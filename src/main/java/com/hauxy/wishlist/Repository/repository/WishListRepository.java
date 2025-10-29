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
        List<WishList> wishlist = dao.getWishLists();


        for (WishList w : wishlist) {
            List<Wish> wishes = getWishesPerWishlist(w);
            w.populateWishesForWishlist(wishes);
        }

        return wishlist;
    }
    public WishList getWishListById(int id) {
        WishList wishlist = dao.getWishListById(id);



        List<Wish> wishes = getWishesPerWishlist(wishlist);
        wishlist.populateWishesForWishlist(wishes);

        return wishlist;
    }
    // UPDATE
    public int updateWishList(WishList newWishList) {
        dao.updateWishList(newWishList);
        dao.updateWishListPerWish(newWishList);
        return 1;
    }

    // DELETE
    public int deleteWishListById(int id) {
        return dao.deleteWishListById(id);
    }

    // Wish
    //CRUD

    // CREATE
    public int createNewWish(Wish newWish) {
        return dao.createNewWish(newWish);
    }

    public Wish insertWish(Wish wish) {
        return dao.insertWish(wish);
    }
    // READ
    public List<Wish> getWishes() {
        return dao.getWishes();
    }
    public Wish getWishById(int id) {
        return dao.getWishById(id);
    }
    // UPDATE
    public int updateWish(Wish newWish, int id) {
        return dao.updateWish(newWish, id);

    }
    // DELETE
    public int deleteWishById(int id) {
        return dao.deleteWishById(id);

    }


    // wishesPerWishlist

    public List<Wish> getWishesPerWishlist(WishList wishlist) {
        return dao.getWishesPerWishlist(wishlist);
    }

    public void updateWishListPerWish(WishList wishlist) {
        dao.updateWishListPerWish(wishlist);

    }

    public void deleteWishFromWishlist(WishList wishlist, Wish wish) {
        dao.deleteWishFromWishlist(wishlist, wish);
    }

    public int deleteWishlistInWishes_per_wishlist(WishList wishlist) {
        deleteWishListById(wishlist.getWichlist_id());
        List<Wish> wishes = wishlist.getWishes();
        for (Wish wish : wishes) {
            deleteWishById(wish.getWish_id());

        }
        return dao.deleteWishlistInWishes_Per_Wishlist(wishlist);
    }


}
