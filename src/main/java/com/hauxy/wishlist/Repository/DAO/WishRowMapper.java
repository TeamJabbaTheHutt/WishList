package com.hauxy.wishlist.Repository.DAO;

import com.hauxy.wishlist.model.Wish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {


    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
        Wish wish = new  Wish();
        wish.setWish_id(rs.getInt("wish_id"));
        wish.setWish_name(rs.getString("wish_name"));
        wish.setWish_price(rs.getDouble("wish_price"));
        wish.setWish_link(rs.getString("wish_link"));
        return wish;
    }


}
