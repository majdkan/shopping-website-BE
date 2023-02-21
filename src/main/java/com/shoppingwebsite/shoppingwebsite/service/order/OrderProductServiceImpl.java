package com.shoppingwebsite.shoppingwebsite.service.order;

import com.shoppingwebsite.shoppingwebsite.model.inventory.Product;
import com.shoppingwebsite.shoppingwebsite.model.inventory.ProductResponse;
import com.shoppingwebsite.shoppingwebsite.model.order.Order;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderProduct;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderProductCount;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderStatus;
import com.shoppingwebsite.shoppingwebsite.repository.order.OrderProductRepository;
import com.shoppingwebsite.shoppingwebsite.service.inventory.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @Override
    public Long createOrderProduct(OrderProduct orderProduct) throws Exception {
        if (orderProduct != null) {
            Product dataProduct = productService.getProductById(orderProduct.getProductId());
            if (dataProduct != null) {
                if (dataProduct.getQuantity() > 0) {
                    Order openOrder = orderService.getOpenOrderByCustomerId(orderProduct.getCustomerId());
                    if (openOrder != null) {
                        orderProduct.setOrderId(openOrder.getId());
                        productService.updateQuantity(dataProduct.getId(),dataProduct.getQuantity() - 1);
                        return orderProductRepository.createOrderProduct(orderProduct);
                    } else {
                        LocalDate date = LocalDate.now();
                        Order newOrder = new Order(null, orderProduct.getCustomerId(), date, null,null, null, null,null, OrderStatus.OPEN);
                        Long newOrderId = orderService.createOrder(newOrder);
                        orderProduct.setOrderId(newOrderId);
                        productService.updateQuantity(dataProduct.getId(),dataProduct.getQuantity() - 1);
                        return orderProductRepository.createOrderProduct(orderProduct);
                    }
                } else {
                    throw new Exception("product is out of stock");
                }
            } else {
                throw new Exception("product id " + dataProduct.getId() + " is not exist");
            }
        } else {
            throw new Exception("orderProduct is empty");
        }
    }


    @Override
    public OrderProduct getOrderProductById(Long id) {
        return orderProductRepository.getOrderProductById(id);
    }

    @Override
    public void updateOrderProductById(Long id, OrderProduct orderProduct) {
        orderProductRepository.updateOrderProductById(id, orderProduct);
    }

    @Override
    public void deleteOrderProductById(Long id) throws Exception {
        if (id != null){
            OrderProduct wantedOrderProductToDelete = orderProductRepository.getOrderProductById(id);
            Product dataProduct = productService.getProductById(wantedOrderProductToDelete.getProductId());
            if (wantedOrderProductToDelete != null){
                Order openOrder = orderService.getOrderById(wantedOrderProductToDelete.getOrderId());
                OrderProductCount count = orderProductRepository.countOrderProductWithOrderId(openOrder.getId());
                if (count.getCount() > 1 ) {
                    productService.updateQuantity(dataProduct.getId(),dataProduct.getQuantity() + 1 );
                    orderProductRepository.deleteOrderProductById(wantedOrderProductToDelete.getId());
                } if ( count.getCount() == 1) {
                    productService.updateQuantity(dataProduct.getId(),dataProduct.getQuantity() + 1 );
                    orderProductRepository.deleteOrderProductById(wantedOrderProductToDelete.getId());
                    orderService.deleteOrderById(openOrder.getId());
                }
            }else {
                throw new Exception("no such order product to delete with this id");
            }
        }else {
            throw new Exception("given id is null");
        }
    }

    @Override
    public List<ProductResponse> getAllOrderProductsByCustomerId(Long customerId) {
        Order openOrder = orderService.getOpenOrderByCustomerId(customerId);
        if (openOrder != null) {
            return orderProductRepository.getAllOrderProductsByCustomerId(customerId, openOrder.getId());
        }else{
            return null;
        }
    }

    @Override
    public List<Product> getAllOrderProductsByOrderId(Long orderId) {
        return orderProductRepository.getAllOrderProductsByOrderId(orderId);
    }


    @Override
    public void updateOrderIdByCustomerId(Long customerId, Long orderId) {
        orderProductRepository.updateOrderIdByCustomerId(customerId, orderId);
    }

    @Override
    public OrderProductCount countOrderProductWithOrderId(Long orderId) {
        return orderProductRepository.countOrderProductWithOrderId(orderId);
    }

    @Override
    public void deleteOrderProductsByCustomerId(Long customerId) {
        orderProductRepository.deleteOrderProductsByCustomerId(customerId);
    }

}
