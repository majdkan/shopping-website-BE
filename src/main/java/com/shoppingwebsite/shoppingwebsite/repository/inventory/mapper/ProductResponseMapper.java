package com.shoppingwebsite.shoppingwebsite.repository.inventory.mapper;

import com.shoppingwebsite.shoppingwebsite.model.inventory.ProductResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductResponseMapper implements RowMapper<ProductResponse> {
    @Override
    public ProductResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProductResponse(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("view_description"),
                rs.getString("full_description"),
                rs.getDouble("price"),
                rs.getString("img"),
                rs.getLong("quantity"),
                rs.getLong("indexId")
        );
    }
}
