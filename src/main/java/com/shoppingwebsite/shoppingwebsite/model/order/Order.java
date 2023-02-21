package com.shoppingwebsite.shoppingwebsite.model.order;

import java.time.LocalDate;

public class Order {
    private Long id;
    private Long customerId;
    private LocalDate orderDate;
    private String country;
    private String city;
    private String phoneNumber;
    private Long totalProducts;
    private Double totalPrice;
    private OrderStatus status;

    public Order(Long id, Long customerId, LocalDate orderDate, String country, String city, String phoneNumber, Long totalProducts, Double totalPrice, OrderStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.totalProducts = totalProducts;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getTotalProducts() {
        return totalProducts;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTotalProducts(Long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}

