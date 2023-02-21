package com.shoppingwebsite.shoppingwebsite.service.customer;

import com.shoppingwebsite.shoppingwebsite.model.customer.WishlistProduct;
import com.shoppingwebsite.shoppingwebsite.model.inventory.Product;
import com.shoppingwebsite.shoppingwebsite.model.inventory.ProductResponse;
import com.shoppingwebsite.shoppingwebsite.repository.customer.WishlistProductRepository;
import com.shoppingwebsite.shoppingwebsite.service.inventory.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistProductServiceImpl implements WishlistProductService {
    @Autowired
    WishlistProductRepository wishlistProductRepository;

    @Autowired
    ProductService productService;

    @Override
    public Long createWishlistProduct(WishlistProduct wishlistProduct) throws Exception {
        if (wishlistProduct != null){
            Product wantedProduct = productService.getProductById(wishlistProduct.getProductId());
            if (wantedProduct != null){
                List<ProductResponse> customerWishlistProducts = wishlistProductRepository.getAllWishlistProductsByCustomerId(wishlistProduct.getCustomerId());
                boolean isFound = false;
                for (ProductResponse product : customerWishlistProducts) {
                    if (product.getId().equals(wantedProduct.getId())) {
                        isFound = true;
                        break;
                    }
                }
                if (isFound) {
                    throw new Exception("product already in wishlist");

                } else {
                    return wishlistProductRepository.createWishlistProduct(wishlistProduct);
                }
            }else {
                throw new Exception("No such product");
            }
        }else {
            throw new Exception("no wishlist product to add");
        }
    }

    @Override
    public WishlistProduct getWishlistProductById(Long customerId) {
        return wishlistProductRepository.getWishlistProductById(customerId);
    }

    @Override
    public void updateWishlistProductById(Long id, WishlistProduct wishlistProduct) {
        wishlistProductRepository.updateWishlistProductById(id, wishlistProduct);
    }

    @Override
    public void deleteWishlistProductById(Long id) {
        wishlistProductRepository.deleteWishlistProductById(id);
    }

    @Override
    public List<ProductResponse> getAllWishlistProductsByCustomerId(Long customerId) {
        return wishlistProductRepository.getAllWishlistProductsByCustomerId(customerId);
    }

    @Override
    public void deleteAllWishlistProductsByCustomerId(Long customerId) {
        wishlistProductRepository.deleteAllWishlistProductsByCustomerId(customerId);
    }


}

