package com.hauxy.wishlist.service;

import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.Repository.repository.WishListRepository;
import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.Wish;
import com.hauxy.wishlist.model.WishList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private WishListRepository wishListRepository;

    public UserService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    // CRUD

    // CREATE
    public String createNewUser(User newUser) {
        if (wishListRepository.createNewUser(newUser) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

    // READ
    public List<User> getUsers() {
        return wishListRepository.getUsers();
    }
    // UPDATE
    public User getUserById(int id) {
        return wishListRepository.getUserById(id);
    }
    public String updateUser(User newUser) {
        if (wishListRepository.updateUser(newUser) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

    // DELETE
    public String deleteUserById(int id) {
        if (wishListRepository.deleteUserById(id) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }


}
