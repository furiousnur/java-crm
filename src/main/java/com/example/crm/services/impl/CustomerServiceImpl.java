package com.example.crm.services.impl;

import com.example.crm.model.Customer;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /*@Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer obj = customerRepository.findByCustomerId(customer.getCustomerId());
//        Customer obj = customerRepository.findByCustomerIdAndName(customer.getCustomerId(), customer.getName());

        if (obj != null){
            obj.setName(customer.getName());
            obj.setEmail(customer.getEmail());
            obj.setAddress(customer.getAddress());
            return customerRepository.save(obj);
        }

        return null;
    }*/

    /*@Override
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }*/

    @Override
    public Map<String, Object> getCustomerList() {
        List<Customer> customers = customerRepository.findAll();
        Map<String, Object> response = new HashMap<>();

        if (!customers.isEmpty()) {
            response.put("status", HttpStatus.OK);
            response.put("success", "Customers found");
            response.put("customers", customers);
        } else {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("error", "Customers not found");
        }

        return response;
    }

    @Override
    public Map<String, Object> save(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        Map<String, Object> response = new HashMap<>();

        if (savedCustomer != null) {
            response.put("status", HttpStatus.CREATED.value()); // Set status to CREATED
            response.put("success", "Customer created successfully");
            response.put("customer", savedCustomer); // Include the created customer details
        } else {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", "Customer not created");
        }

        return response;
    }

    @Override
    public Map<String, Object> update(Customer customer) {
        Customer obj = customerRepository.findByCustomerId(customer.getCustomerId());
        Map<String, Object> response = new HashMap<>();
        if (obj != null) {
            obj.setName(customer.getName());
            obj.setEmail(customer.getEmail());
            obj.setAddress(customer.getAddress());
            Customer updatedCustomer = customerRepository.save(obj);
            response.put("status", HttpStatus.CREATED.value()); // Set status to CREATED
            response.put("success", "Customer updated successfully");
            response.put("customer", updatedCustomer); // Include the created customer details
        } else {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", "Customer not updated");
        }

        return response;
    }

    @Override
    public Map<String, Object> customerFind(Integer customerId) {
        Customer obj = customerRepository.findByCustomerId(customerId);
        Map<String, Object> response = new HashMap<>();

        if (obj != null) {
            response.put("status", HttpStatus.OK);
            response.put("success", "Customer found");
            response.put("customer", obj);
        } else {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("error", "Customer not found");
        }

        return response;
    }

    @Override
    public Map<String, Object> deleteCustomer(Integer customerId) {
        Customer obj = customerRepository.findByCustomerId(customerId);
        Map<String, Object> response = new HashMap<>();

        if (obj != null) {
            customerRepository.deleteById(customerId);
            response.put("status", HttpStatus.OK);
            response.put("success", "Customer " + obj.getName() + " has been deleted successfully");
        } else {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("error", "Customer not found");
        }

        return response;
    }

}
