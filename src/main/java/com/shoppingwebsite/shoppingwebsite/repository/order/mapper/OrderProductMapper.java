package com.shoppingwebsite.shoppingwebsite.repository.order.mapper;

import com.shoppingwebsite.shoppingwebsite.model.order.OrderProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderProductMapper implements RowMapper<OrderProduct> {
    @Override
    public OrderProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OrderProduct(
                rs.getLong("id"),
                rs.getLong("customer_id"),
                rs.getLong("order_id"),
                rs.getLong("product_id"),
                rs.getLong("quantity"),
                rs.getDouble("price")
        );
    }
}

