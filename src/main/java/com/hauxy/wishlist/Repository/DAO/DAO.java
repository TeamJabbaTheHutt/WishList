package com.hauxy.wishlist.Repository.DAO;


import org.apache.catalina.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAO {
    // Man kan vælge at lave UserDAO, WishDAO og WishlistDAO, såmen som JunctionDAO,
    // Vi synes det virker excessive og eventuelt redundant, når vi kan opdele det programmatisk i en DAO

    private final JdbcTemplate jdbc;


    public DAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /// UserDAO
    public List<User> getUsers() {
        List<User> users;

    }




}
