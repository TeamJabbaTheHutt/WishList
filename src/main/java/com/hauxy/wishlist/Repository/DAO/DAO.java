package com.hauxy.wishlist.Repository.DAO;
import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.Wish;
import com.hauxy.wishlist.model.WishList;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DAO {
    // Man kan vælge at lave UserDAO, WishDAO og WishlistDAO, såmen som JunctionDAO,
    // Vi synes det virker excessive og eventuelt redundant, når vi kan opdele det programmatisk i én DAO

    private final JdbcTemplate jdbc;
    private final UserRowMapper userRowMapper = new UserRowMapper();
    private final WishlistRowMapper wishlistRowMapper = new WishlistRowMapper();
    private final WishRowMapper wishRowMapper = new WishRowMapper();

    public DAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /// UserDAO
    // CREATE
    public int createNewUser(User newUser) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        return jdbc.update(sql, newUser.getUsername(), newUser.getEmail(), newUser.getPassword());
    }


    // READ
    public List<User> getUsers() {
        List<User> users;
        String sql = "SELECT * FROM users";
        users = jdbc.query(sql, userRowMapper);
        return users;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbc.queryForObject(sql, userRowMapper, id);
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbc.queryForObject(sql, userRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // UPDATE

    public int updateUser(User newUser) {
        String sql = "UPDATE users SET username = ?, email = ?, password = ? WHERE user_id = ?";
        return jdbc.update(sql, newUser.getUsername(), newUser.getEmail(), newUser.getPassword());
    }

    // DELETE
    public int deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        return jdbc.update(sql, id);
    }




    /// WishListDAO

    // CREATE
    public int createNewWishList(WishList newWishList) {
        String sql = "INSERT INTO wishlist (user_id, wishlist_name) VALUES (?, ?)";
        return jdbc.update(sql, newWishList.getUser_id(), newWishList.getWishlist_name());
    }


    // READ
    public List<WishList> getWishLists() {
        List<WishList> wishLists;
        String sql = "SELECT * FROM wishlist.wishlist";
        wishLists = jdbc.query(sql, wishlistRowMapper);
        return wishLists;
    }

    public WishList getWishListById(int id) {
        String sql = "SELECT * FROM wishlist WHERE wishlist_id = ?";
        return jdbc.queryForObject(sql, wishlistRowMapper, id);
    }

    // UPDATE

    public int updateWishList(WishList newWishList) {
        String sql = "UPDATE wishlist SET wishlist_name = ? WHERE wishlist_id = ?";
        return jdbc.update(sql, newWishList.getWishlist_name(), newWishList.getWichlist_id());

    }


    // DELETE
    public int deleteWishListById(int id) {
        String sql = "DELETE FROM wishlist WHERE wishlist_id = ?";
        return jdbc.update(sql, id);
    }





    /// WishDAO

    // CREATE
    public int createNewWish(Wish newWish) {
        String sql = "INSERT INTO wish (wish_name, wish_price, wish_link) VALUES (?, ?, ?)";
        return jdbc.update(sql, newWish.getWish_name(), newWish.getWish_price(), newWish.getWish_link());
    }


    // READ

    public List<Wish> getWishes() {
        List<Wish> wishes;
        String sql = "SELECT * FROM wish";
        wishes = jdbc.query(sql, wishRowMapper);
        return wishes;
    }

    public Wish getWishById(int id) {
        String sql = "SELECT * FROM wish WHERE wish_id = ?";
        return jdbc.queryForObject(sql, wishRowMapper, id);
    }

    // UPDATE
    public int updateWish(Wish newWish, int id) {
        String sql = "UPDATE wish SET wish_name = ?, wish_price = ?, wish_link = ? WHERE wish_id = ?";
        return jdbc.update(sql, newWish.getWish_name(), newWish.getWish_price(), newWish.getWish_link(), id);
    }


    // DELETE
    public int deleteWishById(int id) {
        String sql = "DELETE FROM wish WHERE wish_id = ?";
        return jdbc.update(sql, id);
    }




    ///  wishes_per_wishlistDAO

    // CREATE

    // READ
    public List<Wish> getWishesPerWishlist(WishList wishList) {
        List<Wish> wishes = new ArrayList<>();
        String sql = "SELECT wish_id FROM wishes_per_wishlist WHERE wishlist_id = ?;";
        List<Integer> wishIds = jdbc.queryForList(sql, Integer.class, wishList.getWichlist_id());

        for (int wishId : wishIds) {
            Wish wish = getWishById(wishId);
            wishes.add(wish);
        }
        return wishes;
    }
    // UPDATE
    @Transactional
    public void updateWishListPerWish(WishList wishList) {
        String sqlDeleteWishesPerWish = "DELETE FROM wishes_per_wishlist WHERE wishlist_id = ?;";
        jdbc.update(sqlDeleteWishesPerWish, wishList.getWichlist_id());

        String sqlInsertWishesPerWishList = "INSERT INTO wishes_per_wishlist (wishlist_id, wish_id) VALUES (?, ?);";
        for(Wish wish : wishList.getWishes()) {
            jdbc.update(sqlInsertWishesPerWishList, wishList.getWichlist_id(), wish.getWish_id());

        }
    }

    @Transactional
    public Wish insertWish(Wish wish) {
        String sql = "INSERT INTO wish (wish_name, wish_price, wish_link) VALUES (?, ?, ?)";
        jdbc.update(sql, wish.getWish_name(), wish.getWish_price(), wish.getWish_link());

        // Retrieve the generated ID (H2 supports identity retrieval)
        Integer newId = jdbc.queryForObject("SELECT MAX(wish_id) FROM wish", Integer.class);
        wish.setWish_id(newId);
        return wish;
    }

    // DELETE
    @Transactional
    public void deleteWishFromWishlist(WishList wishList, Wish wish) {
        String sqlJoin = "DELETE FROM wishes_per_wishlist WHERE wishlist_id = ? AND wish_id = ?";
        jdbc.update(sqlJoin, wishList.getWichlist_id(), wish.getWish_id());

        String sqlWish = "DELETE FROM wish WHERE wish_id = ?";
        jdbc.update(sqlWish, wish.getWish_id());
    }



    public int deleteWishlistInWishes_Per_Wishlist(WishList wishList) {
        String sql = "DELETE FROM wishes_per_wishlist WHERE wishlist_id = ?;";
        return jdbc.update(sql, wishList.getWichlist_id());
    }






}
