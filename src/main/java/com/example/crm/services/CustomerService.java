package com.example.crm.services;

import com.example.crm.model.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    //    Customer save(Customer customer);
    //    Customer update(Customer customer);

    Map<String, Object> save(Customer customer);
    Map<String, Object> update(Customer customer);
    Map<String, Object> getCustomerList();
    Map<String, Object> customerFind(Integer customerId);
    Map<String, Object> deleteCustomer(Integer customerId);
}
