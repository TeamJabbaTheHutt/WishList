package com.hauxy.wishlist.service;

import com.hauxy.wishlist.Repository.DAO.DAO;
import com.hauxy.wishlist.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private DAO dao;

    public UserService(DAO dao) {
        this.dao = dao;
    }

    // CRUD

    // CREATE
    public String createNewUser(User newUser) {
        if (dao.createNewUser(newUser) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

    // READ
    public List<User> getUsers() {
        return dao.getUsers();
    }
    // UPDATE
    public User getUserById(int id) {
        return getUserById(id);
    }
    public String updateUser(User newUser) {
        if (dao.updateUser(newUser) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

    // DELETE
    public String deleteUserById(int id) {
        if (dao.deleteUserById(id) == 1) {
            return "Success";
        } else {
            return "Failed";
        }
    }

}
