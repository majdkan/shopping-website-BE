package com.shoppingwebsite.shoppingwebsite.service.customer;

import com.shoppingwebsite.shoppingwebsite.model.customer.Customer;
import com.shoppingwebsite.shoppingwebsite.model.customer.CustomerProfileResponse;

public interface CustomerService {
    void createCustomer(Customer customer) throws Exception;
    Customer getCustomerById(Long id);
    void updateCustomerById(Long id, Customer customer);
    void deleteCustomerById(Long id) throws Exception;
    Customer getCustomerByUsername(String username);
    Customer getCustomerByEmail(String email);
    CustomerProfileResponse getCustomerProfile(String username) throws Exception;
}

