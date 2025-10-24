package com.hauxy.wishlist.Model;

public class WishList {
    private int wichlist_id;
    private int user_id;
    private String wishlist_name;

    public WishList(int wichlist_id, int user_id, String wishlist_name) {
        this.wichlist_id = wichlist_id;
        this.user_id = user_id;
        this.wishlist_name = wishlist_name;
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


