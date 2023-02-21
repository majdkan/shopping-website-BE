package com.shoppingwebsite.shoppingwebsite.service.order;

import com.shoppingwebsite.shoppingwebsite.model.order.Order;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderList;

import java.util.List;

public interface OrderService {
    Long createOrder(Order order);
    Order getOrderById(Long id);
    void updateOrderById(Long id, Order order) throws Exception;
    void deleteOrderById(Long id);
    Order getOpenOrderByCustomerId(Long customerId);
    List<Order> getClosedOrderByCustomerId(Long customerId);
    void deleteOrdersByCustomerId(Long customerId);
    List<OrderList> getOrderListsByCustomerId(Long customerId) throws Exception;


}

