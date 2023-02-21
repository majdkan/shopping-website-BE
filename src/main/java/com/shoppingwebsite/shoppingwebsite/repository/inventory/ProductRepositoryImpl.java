package com.shoppingwebsite.shoppingwebsite.repository.inventory;

import com.shoppingwebsite.shoppingwebsite.model.inventory.Product;
import com.shoppingwebsite.shoppingwebsite.repository.inventory.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private static final String PRODUCTS_TABLE_NAME = "products";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void createProduct(Product product) {
        String sql = "INSERT INTO " + PRODUCTS_TABLE_NAME + " (name, view_description, full_description, price, img, quantity) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getViewDescription(), product.getFullDescription(), product.getPrice(), product.getImg(), product.getQuantity());
    }

    @Override
    public Product getProductById(Long id) {
        String sql = "SELECT * FROM " + PRODUCTS_TABLE_NAME + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new ProductMapper(), id);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public void updateProductById(Long id, Product product) {
        String sql = "UPDATE " + PRODUCTS_TABLE_NAME + " SET name=?, view_description=?, full_description=?, price=?, img=?, quantity=? " +
                "WHERE id=?";
        jdbcTemplate.update(sql, product.getName(), product.getViewDescription(), product.getFullDescription(), product.getPrice(), product.getImg(), product.getQuantity(), id);
    }

    @Override
    public void deleteProductById(Long id) {
        String sql = "DELETE FROM " + PRODUCTS_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM " + PRODUCTS_TABLE_NAME;
        try {
            return jdbcTemplate.query(sql, new ProductMapper());
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }



    @Override
    public List<Product> getAllAvailableProducts() {
        String sql = "SELECT * FROM " + PRODUCTS_TABLE_NAME + " WHERE QUANTITY > 0";
        try {
            return jdbcTemplate.query(sql, new ProductMapper());
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public void updateQuantity (Long productId, Long quantityToUpdate){
        String sql = "UPDATE " + PRODUCTS_TABLE_NAME +" SET quantity = ? WHERE id = ?";
        jdbcTemplate.update(sql, quantityToUpdate, productId);
    }


}
