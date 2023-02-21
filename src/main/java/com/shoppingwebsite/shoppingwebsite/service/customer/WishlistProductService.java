package com.shoppingwebsite.shoppingwebsite.service.customer;

import com.shoppingwebsite.shoppingwebsite.model.customer.WishlistProduct;
import com.shoppingwebsite.shoppingwebsite.model.inventory.ProductResponse;

import java.util.List;

public interface WishlistProductService {
    Long createWishlistProduct(WishlistProduct wishlistProduct) throws Exception;
    WishlistProduct getWishlistProductById(Long id);
    void updateWishlistProductById(Long id, WishlistProduct wishlistProduct);
    void deleteWishlistProductById(Long id);
    List<ProductResponse> getAllWishlistProductsByCustomerId(Long customerId);
    void deleteAllWishlistProductsByCustomerId(Long customerId);


}

