package com.hauxy.wishlist.service;

import com.hauxy.wishlist.Repository.repository.WishListRepository;
import com.hauxy.wishlist.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private final WishListRepository wishListRepository;

    public LoginService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public User getUser(String email) {
        List<User> users = wishListRepository.getUsers();
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                return user;
            }
        }

        return null;
    }

    public boolean checkCredentials(String email, String password) {
        User user = getUser(email);

        if (user.getPassword().equals(password)) {
            return true;
        }

        return false;
    }

}
