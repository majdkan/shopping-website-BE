package com.shoppingwebsite.shoppingwebsite.repository.order;

import com.shoppingwebsite.shoppingwebsite.model.order.Order;

import java.util.List;

public interface OrderRepository {
    Long createOrder(Order order);
    Order getOrderById(Long id);
    void updateOrderById(Long id, Order order);
    void deleteOrderById(Long id);
    Order getOpenOrderByCustomerId(Long customerId);
    List<Order> getClosedOrderByCustomerId(Long customerId);
    void deleteOrdersByCustomerId(Long customerId);

}

