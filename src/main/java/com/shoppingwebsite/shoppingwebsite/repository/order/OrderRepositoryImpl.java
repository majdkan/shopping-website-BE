package com.shoppingwebsite.shoppingwebsite.repository.order;

import com.shoppingwebsite.shoppingwebsite.model.order.Order;
import com.shoppingwebsite.shoppingwebsite.repository.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private static final String ORDERS_TABLE_NAME = "orders";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long createOrder(Order order) {
        String sql = "INSERT INTO " + ORDERS_TABLE_NAME + " (customer_id, order_date, country, city, phone_number, total_products, total_price, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, order.getCustomerId(), order.getOrderDate(), order.getCountry(), order.getCity(), order.getPhoneNumber(), order.getTotalProducts(), order.getTotalPrice(), order.getStatus().name());
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", Long.class);
    }

    @Override
    public Order getOrderById(Long id) {
        String sql = "SELECT * FROM " + ORDERS_TABLE_NAME + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new OrderMapper(), id);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public void updateOrderById(Long id, Order order) {
        String sql = "UPDATE " + ORDERS_TABLE_NAME + " SET customer_id=?, order_date=?, country=?, city=?, phone_number=?, total_products=?, total_price=?, status=? " +
                "WHERE id=?";
        jdbcTemplate.update(sql, order.getCustomerId(), order.getOrderDate(), order.getCountry(), order.getCity(), order.getPhoneNumber(), order.getTotalProducts(), order.getTotalPrice(), order.getStatus().name(), id);
    }

    @Override
    public void deleteOrderById(Long id) {
        String sql = "DELETE FROM " + ORDERS_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Order getOpenOrderByCustomerId(Long customerId) {
        String sql = "SELECT * FROM " + ORDERS_TABLE_NAME + " WHERE customer_id=? AND status= 'OPEN'";
        try {
            return jdbcTemplate.queryForObject(sql, new OrderMapper(), customerId);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public List<Order> getClosedOrderByCustomerId(Long customerId) {
        String sql = "SELECT * FROM " + ORDERS_TABLE_NAME + " WHERE customer_id=? AND status= 'CLOSED'";
        try {
            return jdbcTemplate.query(sql, new OrderMapper(), customerId);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }


    @Override
    public void deleteOrdersByCustomerId(Long customerId) {
        String sql = "DELETE FROM " + ORDERS_TABLE_NAME + " WHERE customer_id=?";
        jdbcTemplate.update(sql, customerId);
    }
}

