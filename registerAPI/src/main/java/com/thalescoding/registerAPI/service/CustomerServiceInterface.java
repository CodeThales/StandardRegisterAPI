package com.thalescoding.registerAPI.service;

import com.thalescoding.registerAPI.model.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);
}
