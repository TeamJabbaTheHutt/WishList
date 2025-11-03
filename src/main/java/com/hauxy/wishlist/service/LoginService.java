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

    public boolean checkCredentials(String email, String password) {
        User user = wishListRepository.getUserByEmail(email);

        if (user == null) return false;

        return user.getPassword().equals(password);
    }



}
