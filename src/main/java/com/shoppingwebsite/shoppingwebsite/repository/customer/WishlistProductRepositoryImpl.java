package com.shoppingwebsite.shoppingwebsite.repository.customer;
import com.shoppingwebsite.shoppingwebsite.model.customer.WishlistProduct;
import com.shoppingwebsite.shoppingwebsite.model.inventory.ProductResponse;
import com.shoppingwebsite.shoppingwebsite.repository.customer.mapper.WishlistProductMapper;
import com.shoppingwebsite.shoppingwebsite.repository.inventory.mapper.ProductResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistProductRepositoryImpl implements WishlistProductRepository {
    private static final String WISHLIST_PRODUCTS_TABLE_NAME = "wish_list";
    private static final String PRODUCTS_TABLE_NAME = "products";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long createWishlistProduct(WishlistProduct wishlistProduct) {
        String sql = "INSERT INTO " + WISHLIST_PRODUCTS_TABLE_NAME + " (customer_id, product_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, wishlistProduct.getCustomerId(), wishlistProduct.getProductId());
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", Long.class);
    }

    @Override
    public WishlistProduct getWishlistProductById(Long id) {
        String sql = "SELECT * FROM " + WISHLIST_PRODUCTS_TABLE_NAME + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new WishlistProductMapper(), id);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public void updateWishlistProductById(Long id, WishlistProduct wishlistProduct) {
        String sql = "UPDATE " + WISHLIST_PRODUCTS_TABLE_NAME + " SET customer_id=?, product_id=? WHERE id=?";
        jdbcTemplate.update(sql, wishlistProduct.getCustomerId(), wishlistProduct.getProductId(), id);
    }

    @Override
    public void deleteWishlistProductById(Long id) {
        String sql = "DELETE FROM " + WISHLIST_PRODUCTS_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<ProductResponse> getAllWishlistProductsByCustomerId(Long customerId) {
        String sql ="SELECT "+ PRODUCTS_TABLE_NAME +".*, " + WISHLIST_PRODUCTS_TABLE_NAME +".id as indexId FROM " + PRODUCTS_TABLE_NAME +
                " INNER JOIN " + WISHLIST_PRODUCTS_TABLE_NAME + " ON " + PRODUCTS_TABLE_NAME+ ".id = " + WISHLIST_PRODUCTS_TABLE_NAME + ".product_id" +
                " WHERE " + WISHLIST_PRODUCTS_TABLE_NAME + ".customer_id=?";
        try {
            return jdbcTemplate.query(sql, new ProductResponseMapper(), customerId);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public void deleteAllWishlistProductsByCustomerId(Long customerId) {
        String sql = "DELETE FROM " + WISHLIST_PRODUCTS_TABLE_NAME + " WHERE customer_id=?";
        jdbcTemplate.update(sql, customerId);
    }




}
