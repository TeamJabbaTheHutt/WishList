package com.hauxy.wishlist.model;

import java.util.ArrayList;
import java.util.List;

public class WishList {
    private int wichlist_id;
    private int user_id;
    private String wishlist_name;
    private List<Wish> wishes =  new ArrayList<>();


    public WishList() {}
    public WishList(int wichlist_id, int user_id, String wishlist_name) {
        this.wichlist_id = wichlist_id;
        this.user_id = user_id;
        this.wishlist_name = wishlist_name;
    }

    // CRUD
    public void populateWishesForWishlist(List<Wish> wishes) {
        this.wishes = wishes;
    }

    public void insertNewWish(Wish wish) {
        wishes.add(wish);
    }
    public List<Wish> getWishes() {
        return wishes;
    }

    public void updateWish(Wish updatedWish) {
        for (Wish wishInList : wishes) {
            if (wishInList.getWish_id() == updatedWish.getWish_id()) {
                wishInList.setWish_name(updatedWish.getWish_name());
                wishInList.setWish_link(updatedWish.getWish_link());
                wishInList.setWish_price(updatedWish.getWish_price());
                break;
            }
        }
    }

    public void deleteWish(Wish wishToDelete) {
        for (Wish wishInList : wishes) {
            if (wishInList.getWish_id() == wishToDelete.getWish_id()) {
                wishes.remove(wishInList);
            }
        }
    }



    public int getWichlist_id() {
        return wichlist_id;
    }

    public void setWichlist_id(int wichlist_id) {
        this.wichlist_id = wichlist_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getWishlist_name() {
        return wishlist_name;
    }

    public void setWishlist_name(String wishlist_name) {
        this.wishlist_name = wishlist_name;
    }
}


