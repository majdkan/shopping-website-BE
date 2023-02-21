package com.shoppingwebsite.shoppingwebsite.service.order;

import com.shoppingwebsite.shoppingwebsite.model.customer.Customer;
import com.shoppingwebsite.shoppingwebsite.model.order.Order;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderList;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderStatus;
import com.shoppingwebsite.shoppingwebsite.repository.order.OrderRepository;
import com.shoppingwebsite.shoppingwebsite.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    CustomerService customerService;

    @Override
    public Long createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public void updateOrderById(Long id, Order order) throws Exception {
        if (order != null){
            Customer existCustomer = customerService.getCustomerById(order.getCustomerId());
            if (existCustomer != null){
                Order curOrder = orderRepository.getOpenOrderByCustomerId(existCustomer.getId());
                if(curOrder.getStatus().equals(OrderStatus.OPEN)){
                    order.setStatus(OrderStatus.CLOSED);
                    orderRepository.updateOrderById(id, order);
                }else{
                    throw new Exception("Status Already Closed");
                }
            }else {
                throw new Exception("customer does not exist");
            }
        }else {
            throw new Exception("order is empty");
        }
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteOrderById(id);
    }

    @Override
    public Order getOpenOrderByCustomerId(Long customerId) {
        return orderRepository.getOpenOrderByCustomerId(customerId);
    }

    @Override
    public List<Order> getClosedOrderByCustomerId(Long customerId) {
        return orderRepository.getClosedOrderByCustomerId(customerId);
    }


    @Override
    public void deleteOrdersByCustomerId(Long customerId) {
        orderRepository.deleteOrdersByCustomerId(customerId);
    }

    @Override
    public List<OrderList> getOrderListsByCustomerId(Long customerId) throws Exception {
        if (customerId != null){
            Customer curCustomer = customerService.getCustomerById(customerId);
            if (curCustomer != null){
                List<OrderList> orderListsToResponse = new ArrayList<>();
                List<Order> orders = orderRepository.getClosedOrderByCustomerId(customerId);
                if (orders != null && !orders.isEmpty()){
                    for (int i = 0 ; i < orders.size(); i ++){
                        OrderList curOrderList = new OrderList();
                        curOrderList.setOrder(orders.get(i));
                        curOrderList.setProducts(orderProductService.getAllOrderProductsByOrderId(curOrderList.getOrder().getId()));
                        orderListsToResponse.add(curOrderList);
                    }
                    return orderListsToResponse;
                }else {
                    return orderListsToResponse;
                }
            }else {
                throw new Exception("customer with this id not exist");
            }
        }else {
            throw new Exception("no customer id");
        }
    }

}

