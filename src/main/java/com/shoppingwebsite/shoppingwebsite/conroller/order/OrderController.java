package com.shoppingwebsite.shoppingwebsite.conroller.order;

import com.shoppingwebsite.shoppingwebsite.model.order.Order;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderList;
import com.shoppingwebsite.shoppingwebsite.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @CrossOrigin
    @PostMapping(value = "/create")
    public Long createOrder(@RequestBody Order order) throws Exception {
        return orderService.createOrder(order);
    }
    @CrossOrigin
    @GetMapping(value = "/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}/update")
    public void updateOrderById(@PathVariable Long id,
                                @RequestBody Order order ) throws Exception {
        orderService.updateOrderById(id, order);
    }
    @CrossOrigin
    @DeleteMapping(value = "/{id}/delete")
    public void deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);
    }

    @CrossOrigin
    @GetMapping(value = "/{customerId}/open")
    public Order getOpenOrderByCustomerId(@PathVariable Long customerId){
        return orderService.getOpenOrderByCustomerId(customerId);
    }

    @CrossOrigin
    @GetMapping(value = "/{customerId}/lastOrders")
    public List<OrderList> getOrderListsByCustomerId(@PathVariable Long customerId) throws Exception {
        return orderService.getOrderListsByCustomerId(customerId);
    }

}

