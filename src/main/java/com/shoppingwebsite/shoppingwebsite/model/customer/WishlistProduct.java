package com.shoppingwebsite.shoppingwebsite.model.customer;

public class WishlistProduct {

        private Long id;
        private Long customerId;
        private Long productId;

    public WishlistProduct(Long id, Long customerId, Long productId) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
    }

        public Long getId () {
        return id;
    }

        public Long getCustomerId () {
        return customerId;
    }

        public Long getProductId () {
        return productId;
    }

        public void setId (Long id){
        this.id = id;
    }

        public void setCustomerId (Long customerId){
        this.customerId = customerId;
    }

        public void setProductId (Long productId){
        this.productId = productId;
    }
    }

