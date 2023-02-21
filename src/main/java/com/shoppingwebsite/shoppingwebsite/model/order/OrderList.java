package com.shoppingwebsite.shoppingwebsite.model.order;

import com.shoppingwebsite.shoppingwebsite.model.inventory.Product;

import java.util.List;

public class OrderList {
    private Order order;
    private List<Product> products;

    public OrderList() {
        this.order = order;
        this.products = products;
    }

    public Order getOrder() {
        return order;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

