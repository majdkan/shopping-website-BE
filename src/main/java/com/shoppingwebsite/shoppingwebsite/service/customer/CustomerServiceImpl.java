package com.shoppingwebsite.shoppingwebsite.service.customer;

import com.shoppingwebsite.shoppingwebsite.model.customer.Customer;
import com.shoppingwebsite.shoppingwebsite.model.customer.CustomerProfileResponse;
import com.shoppingwebsite.shoppingwebsite.model.inventory.ProductResponse;
import com.shoppingwebsite.shoppingwebsite.model.order.OrderList;
import com.shoppingwebsite.shoppingwebsite.repository.customer.CustomerRepository;
import com.shoppingwebsite.shoppingwebsite.service.order.OrderProductService;
import com.shoppingwebsite.shoppingwebsite.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    WishlistProductService wishlistProductService;
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    OrderService orderService;


    @Override
    public void createCustomer(Customer customer) throws Exception {
        if (customer != null){
            Customer customerByUsername = customerRepository.getCustomerByUsername(customer.getUsername());
            if (customerByUsername == null ){
                Customer customerByEmail = customerRepository.getCustomerByEmail(customer.getEmail());
                if (customerByEmail == null){
                    customerRepository.createCustomer(customer);
                }else {
                    throw new Exception("Email is taken");
                }
            }else {
                throw new Exception("Username Taken");
            }
        }else {
            throw new Exception("Given customer is null");
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public void updateCustomerById(Long id, Customer customer) {
        customerRepository.updateCustomerById(id, customer);
    }

    @Override
    public void deleteCustomerById(Long id) throws Exception {
        if (id != null){
            Customer cutsomerToDelete = customerRepository.getCustomerById(id);
            if (cutsomerToDelete != null){
                wishlistProductService.deleteAllWishlistProductsByCustomerId(id);
                orderProductService.deleteOrderProductsByCustomerId(id);
                orderService.deleteOrdersByCustomerId(id);
                customerRepository.deleteCustomerById(id);
            }else{
                throw new Exception("No such customer with this id " + id);
            }
        }else {
            throw new Exception("Id is null");
        }
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerRepository.getCustomerByUsername(username);
    }
    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    @Override
    public CustomerProfileResponse getCustomerProfile(String username) throws Exception {
        Customer curCustomer = customerRepository.getCustomerByUsername(username);
        List<ProductResponse> curWishlistProducts = wishlistProductService.getAllWishlistProductsByCustomerId(curCustomer.getId());
        List<ProductResponse> curCartProducts = orderProductService.getAllOrderProductsByCustomerId(curCustomer.getId());
        List<OrderList> curOrderList = orderService.getOrderListsByCustomerId(curCustomer.getId());

        CustomerProfileResponse existCustomerProfile = new CustomerProfileResponse(
                curCustomer,
                curWishlistProducts,
                curCartProducts,
                curOrderList
        );
        return existCustomerProfile;
    }
}


