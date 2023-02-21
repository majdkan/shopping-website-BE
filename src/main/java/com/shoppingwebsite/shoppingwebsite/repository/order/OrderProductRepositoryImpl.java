package com.shoppingwebsite.shoppingwebsite.repository.order;

import com.shoppingwebsite.shoppingwebsite.model.inventory.Product;
import com.shoppingwebsite.shoppingwebsite.model.inventory.ProductResponse;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderProduct;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderProductCount;
import com.shoppingwebsite.shoppingwebsite.repository.inventory.mapper.ProductMapper;
import com.shoppingwebsite.shoppingwebsite.repository.inventory.mapper.ProductResponseMapper;
import com.shoppingwebsite.shoppingwebsite.repository.order.mapper.OrderProductCountMapper;
import com.shoppingwebsite.shoppingwebsite.repository.order.mapper.OrderProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderProductRepositoryImpl implements OrderProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String ORDER_PRODUCTS_TABLE_NAME = "order_products";
    private static final String ORDER_TABLE_NAME = "orders";
    private static final String PRODUCTS_TABLE_NAME = "products";


    @Override
    public Long createOrderProduct(OrderProduct orderProduct) {
        String sql = "INSERT INTO " + ORDER_PRODUCTS_TABLE_NAME + " (order_id, customer_id, product_id, quantity, price) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, orderProduct.getOrderId(), orderProduct.getCustomerId() , orderProduct.getProductId(), orderProduct.getQuantity(), orderProduct.getPrice());
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", Long.class);
    }

    @Override
    public OrderProduct getOrderProductById(Long id) {
        String sql = "SELECT * FROM " + ORDER_PRODUCTS_TABLE_NAME + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new OrderProductMapper(), id);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public void updateOrderProductById(Long id, OrderProduct orderProduct) {

    }

    @Override
    public void deleteOrderProductById(Long id) {
        String sql = "DELETE FROM " + ORDER_PRODUCTS_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public List<ProductResponse> getAllOrderProductsByCustomerId(Long customerId, Long orderId) {
        String sql = "SELECT " + PRODUCTS_TABLE_NAME + ".*, " + ORDER_PRODUCTS_TABLE_NAME + ".id as indexId FROM " + PRODUCTS_TABLE_NAME +
                " INNER JOIN " + ORDER_PRODUCTS_TABLE_NAME + " ON " + PRODUCTS_TABLE_NAME + ".id = " + ORDER_PRODUCTS_TABLE_NAME + ".product_id" +
                " WHERE " + ORDER_PRODUCTS_TABLE_NAME + ".customer_id =? AND " + ORDER_PRODUCTS_TABLE_NAME + ".order_id=?";
        try {
            return jdbcTemplate.query(sql, new ProductResponseMapper(), customerId, orderId);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public List<Product> getAllOrderProductsByOrderId(Long orderId) {
        String sql = "SELECT p.* FROM " + ORDER_PRODUCTS_TABLE_NAME + " op JOIN " + PRODUCTS_TABLE_NAME + " p ON op.product_id = p.id " +
                " WHERE op.order_id=?";
        try {
            return jdbcTemplate.query(sql, new ProductMapper(), orderId);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }


    public void updateOrderIdByCustomerId(Long customerId, Long orderId) {
        String sql = "UPDATE " + ORDER_PRODUCTS_TABLE_NAME +" SET order_id = ? WHERE customer_id = ?";
        jdbcTemplate.update(sql, orderId, customerId);
    }

    @Override
    public OrderProductCount countOrderProductWithOrderId(Long orderId) {
        String sql = "SELECT order_id, COUNT(*) AS COUNT FROM " + ORDER_PRODUCTS_TABLE_NAME +" WHERE ORDER_ID = ? GROUP BY order_id;";
        return jdbcTemplate.queryForObject(sql, new OrderProductCountMapper(), orderId);
    }

    @Override
    public void deleteOrderProductsByCustomerId(Long customerId) {
        String sql = "DELETE FROM " + ORDER_PRODUCTS_TABLE_NAME + " WHERE customer_id=?";
        jdbcTemplate.update(sql,customerId);
    }
}

