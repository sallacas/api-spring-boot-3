package com.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @GetMapping("/getCustomers")
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    record NewCustomerRequest(
        String name,
        String email,
        Integer age
    ){ }
    @PostMapping("/insertCustomer")
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);

    }
    @PutMapping("/updateCustomer/{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request){
        Customer update = customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not exists with id "+id));
        update.setName(request.name());
        update.setEmail(request.email());
        update.setAge(request.age());
        customerRepository.save(update);
    }
    @DeleteMapping("/deleteCustomer/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }
}
