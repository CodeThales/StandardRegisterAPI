package com.thalescoding.registerAPI.controller;

import com.thalescoding.registerAPI.model.Customer;
import com.thalescoding.registerAPI.service.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServiceInterface customerService;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Customer>> getCustomers(){
        //Customer customer = new Customer(1L, "ThalesCode", "codethales@gmail.com", "99999-9999");
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }


}
