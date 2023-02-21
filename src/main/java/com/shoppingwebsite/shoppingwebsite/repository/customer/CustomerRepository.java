package com.shoppingwebsite.shoppingwebsite.repository.customer;

import com.shoppingwebsite.shoppingwebsite.model.customer.Customer;
import com.shoppingwebsite.shoppingwebsite.model.customer.CustomerProfileResponse;

public interface CustomerRepository {
    void createCustomer(Customer customer);
    Customer getCustomerById(Long id);
    void updateCustomerById(Long id, Customer customer);
    void deleteCustomerById(Long id);
    Customer getCustomerByUsername(String username);
    Customer getCustomerByEmail(String email);
}

