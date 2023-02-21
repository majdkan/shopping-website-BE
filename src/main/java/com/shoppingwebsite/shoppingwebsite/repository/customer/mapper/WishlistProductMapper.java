package com.shoppingwebsite.shoppingwebsite.repository.customer.mapper;

import com.shoppingwebsite.shoppingwebsite.model.customer.WishlistProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistProductMapper implements RowMapper<WishlistProduct> {
    @Override
    public WishlistProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WishlistProduct(
                rs.getLong("id"),
                rs.getLong("customer_id"),
                rs.getLong("product_id")
        );
    }
}