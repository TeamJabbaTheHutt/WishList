package com.hauxy.wishlist.Repository.DAO;
import com.hauxy.wishlist.model.User;
import com.hauxy.wishlist.model.Wish;
import com.hauxy.wishlist.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
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
        String sql = "SELECT * FROM users;";
        users = jdbc.query(sql, userRowMapper);
        return users;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?;";
        return jdbc.queryForObject(sql, userRowMapper, id);
    }

    // UPDATE

    public int updateUser(User newUser) {
        String sql = "UPDATE users SET username = ?, email = ?, password = ? WHERE user_id = ?;";
        return jdbc.update(sql, newUser.getUsername(), newUser.getEmail(), newUser.getPassword());
    }

    // DELETE
    public int deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?;";
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
        String sql = "SELECT * FROM wishlists;";
        wishLists = jdbc.query(sql, wishlistRowMapper);
        return wishLists;
    }

    public WishList getWishListById(int id) {
        String sql = "SELECT * FROM wishlists WHERE wishlist_id = ?;";
        return jdbc.queryForObject(sql, wishlistRowMapper, id);
    }

    // UPDATE
    public int updateWishList(WishList newWishList) {
        String sql =  "UPDATE wishlists SET wishlist_name = ? WHERE wishlist_id = ?;";
        return jdbc.update(sql, newWishList.getUser_id(), newWishList.getWishlist_name());
    }

    // DELETE
    public int deleteWishListById(int id) {
        String sql = "DELETE FROM wishlists WHERE wishlist_id = ?;";
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
        String sql = "SELECT * FROM wishes;";
        wishes = jdbc.query(sql, wishRowMapper);
        return wishes;
    }

    public Wish getWishById(int id) {
        String sql = "SELECT * FROM wishes WHERE wish_id = ?;";
        return jdbc.queryForObject(sql, wishRowMapper, id);
    }

    // UPDATE
    public int updateWish(Wish newWish) {
        String sql = "UPDATE wish SET wish_name = ?, wish_price = ? WHERE wish_id = ?;";
        return jdbc.update(sql, newWish.getWish_name(), newWish.getWish_price(), newWish.getWish_link());
    }


    // DELETE
    public int deleteWishById(int id) {
        String sql = "DELETE FROM wish WHERE wish_id = ?;";
        return jdbc.update(sql, id);
    }




    ///  wishes_per_wishlistDAO

    // CREATE

    // READ
    public List<Wish> getWishesPerWishlistID(WishList wishList) {
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
    public void updateWishListPerWishlist(WishList wishList) {
        String sqlDeleteWishesPerWishlist = "DELETE FROM wishes_per_wishlist WHERE wishlist_id = ?;";
        jdbc.update(sqlDeleteWishesPerWishlist, wishList.getWichlist_id());

        String sqlInsertWishesPerWishList = "INSERT INTO wishes_per_wishlist (wishlist_id, wish_id) VALUES (?, ?);";
        for(Wish wish : wishList.getWishes()) {
            jdbc.update(sqlInsertWishesPerWishList, wishList.getWichlist_id(), wish.getWish_id());

        }
    }

    // DELETE
    public int deleteWishFromWishlist(WishList wishList, Wish wish) {
        String sql = "DELETE FROM wishes_per_wishlist WHERE wish_id = ?";
        return jdbc.update(sql, wish.getWish_id());
    }

    public int deleteWishlistInWishes_Per_Wishlist(WishList wishList) {
        String sql = "DELETE FROM wishes_per_wishlist WHERE wishlist_id = ?;";
        return jdbc.update(sql, wishList.getWichlist_id());
    }
}
