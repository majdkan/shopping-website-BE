package com.shoppingwebsite.shoppingwebsite.conroller.order;

import com.shoppingwebsite.shoppingwebsite.model.inventory.ProductResponse;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderProduct;
import com.shoppingwebsite.shoppingwebsite.service.order.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orderProduct")
public class OrderProductController {

    @Autowired
    OrderProductService orderProductService;
    @CrossOrigin
    @PostMapping(value = "/create")
    public Long createOrderProduct(@RequestBody OrderProduct orderProduct) throws Exception {
        return orderProductService.createOrderProduct(orderProduct);
    }
    @CrossOrigin
    @GetMapping(value = "/{id}")
    public OrderProduct getOrderProductById(@PathVariable Long id){
        return orderProductService.getOrderProductById(id);
    }
    @CrossOrigin
    @PutMapping(value = "/{id}/update")
    public void updateOrderProductById(@PathVariable Long id,
                                       @RequestBody OrderProduct orderProduct ){
        orderProductService.updateOrderProductById(id, orderProduct);
    }
    @CrossOrigin
    @DeleteMapping(value = "/{id}/delete")
    public void deleteOrderProductById(@PathVariable Long id) throws Exception {
        orderProductService.deleteOrderProductById(id);
    }

    @CrossOrigin
    @GetMapping(value = "/{customerId}/all")
    public List<ProductResponse> getAllOrderProductsByCustomerId(@PathVariable Long customerId){
        return orderProductService.getAllOrderProductsByCustomerId(customerId);
    }
}

