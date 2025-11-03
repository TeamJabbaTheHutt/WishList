package com.hauxy.wishlist.model;

import java.util.ArrayList;
import java.util.List;

public class WishList {
    private int wishListId;
    private int userId;
    private String wishListName;
    private List<Wish> wishes =  new ArrayList<>();


    public WishList() {}
    public WishList(int userId, String wishListName) {
        this.userId = userId;
        this.wishListName = wishListName;
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



    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWishListName() {
        return wishListName;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }
}


