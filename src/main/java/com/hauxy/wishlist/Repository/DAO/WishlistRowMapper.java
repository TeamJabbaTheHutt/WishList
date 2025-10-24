package com.hauxy.wishlist.Repository.DAO;

import com.hauxy.wishlist.model.WishList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistRowMapper implements RowMapper<WishList> {

    @Override
    public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
        WishList wishlist = new WishList();
        wishlist.setWichlist_id(rs.getInt("wichlist_id"));
        wishlist.setUser_id(rs.getInt("user_id"));
        wishlist.setWishlist_name(rs.getString("wishlist_name"));
        return wishlist;
    }
}
