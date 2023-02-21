package com.shoppingwebsite.shoppingwebsite.repository.customer;

import com.shoppingwebsite.shoppingwebsite.model.customer.Customer;
import com.shoppingwebsite.shoppingwebsite.repository.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private static final String CUSTOMERS_TABLE_NAME = "customers";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO " + CUSTOMERS_TABLE_NAME + " (first_name, last_name, email, phone_number, username, password, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhoneNumber(), customer.getUsername(), customer.getPassword(), customer.getAddress());
    }

    @Override
    public Customer getCustomerById(Long id) {
        String sql = "SELECT * FROM " + CUSTOMERS_TABLE_NAME + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new CustomerMapper(), id);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public void updateCustomerById(Long id, Customer customer) {
        String sql = "UPDATE " + CUSTOMERS_TABLE_NAME + " SET first_name=?, last_name=?, email=?, phone_number=?, username=?, password=?, address=? " +
                "WHERE id=?";
        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhoneNumber(), customer.getUsername(), customer.getPassword(), customer.getAddress(), id);
    }

    @Override
    public void deleteCustomerById(Long id) {
        String sql = "DELETE FROM " + CUSTOMERS_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        String sql = "SELECT * FROM " + CUSTOMERS_TABLE_NAME + " WHERE username=?";
        try {
            return jdbcTemplate.queryForObject(sql, new CustomerMapper(), username);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM " + CUSTOMERS_TABLE_NAME + " WHERE email=?";
        try {
            return jdbcTemplate.queryForObject(sql, new CustomerMapper(), email);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

}
