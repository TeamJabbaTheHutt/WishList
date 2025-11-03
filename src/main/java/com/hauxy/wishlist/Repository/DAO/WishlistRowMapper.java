package com.hauxy.wishlist.Repository.DAO;

import com.hauxy.wishlist.model.WishList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistRowMapper implements RowMapper<WishList> {

    @Override
    public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
        WishList wishlist = new WishList();
        wishlist.setWishListId(rs.getInt("wishlist_id"));
        wishlist.setUserId(rs.getInt("user_id"));
        wishlist.setWishListName(rs.getString("wishlist_name"));
        return wishlist;
    }
}
