package com.shoppingwebsite.shoppingwebsite.repository.inventory;

import com.shoppingwebsite.shoppingwebsite.model.inventory.Product;

import java.util.List;

public interface ProductRepository {
    void createProduct(Product product);
    Product getProductById(Long id);
    void updateProductById(Long id, Product product);
    void deleteProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getAllAvailableProducts();
    void updateQuantity (Long productId, Long quantityToUpdate);
}