package com.shoppingwebsite.shoppingwebsite.repository.order;

import com.shoppingwebsite.shoppingwebsite.model.inventory.Product;
import com.shoppingwebsite.shoppingwebsite.model.inventory.ProductResponse;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderProduct;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderProductCount;

import java.util.List;

public interface OrderProductRepository {
    Long createOrderProduct(OrderProduct orderProduct);
    OrderProduct getOrderProductById(Long id);
    void updateOrderProductById(Long id, OrderProduct orderProduct);
    void deleteOrderProductById(Long id);
    List<ProductResponse> getAllOrderProductsByCustomerId(Long customerId, Long orderId);
    List<Product> getAllOrderProductsByOrderId(Long orderId);
    void updateOrderIdByCustomerId(Long customerId, Long orderId);
    OrderProductCount countOrderProductWithOrderId(Long orderId);
    void deleteOrderProductsByCustomerId(Long customerId);


}

