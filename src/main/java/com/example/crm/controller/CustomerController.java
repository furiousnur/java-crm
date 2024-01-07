package com.example.crm.controller;

import com.example.crm.model.Customer;
import com.example.crm.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /*@GetMapping
    public List<Customer> customerList(){
        return customerService.getCustomerList();
    }*/
    //@PostMapping("/save")
   /* @PostMapping
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer){
        return customerService.update(customer);
    }
    */
    @GetMapping
    public ResponseEntity<Object> customerList() {
        Map<String, Object> response = customerService.getCustomerList();

        if (response.containsKey("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Customer customer){
        Map<String, Object> response = customerService.save(customer);
        if (response.containsKey("success")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response); // Use CREATED status for successful creation
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Customer customer){
        Map<String, Object> response = customerService.update(customer);
        if (response.containsKey("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> customerFind(@PathVariable Integer customerId) {
        Map<String, Object> response = customerService.customerFind(customerId);

        if (response.containsKey("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Integer customerId) {
        Map<String, Object> response = customerService.deleteCustomer(customerId);
        if (response.containsKey("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
