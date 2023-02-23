package com.example.SpringCrudApp.service;


import com.example.SpringCrudApp.dto.CustomerDTO;
import com.example.SpringCrudApp.entity.Customer;
import com.example.SpringCrudApp.repository.CustomerRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getName(), customerDTO.getAddress(),customerDTO.getEmail());
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());
    }

    public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = getCustomer(id);
        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setAddress(customerDTO.getAddress());
        existingCustomer.setEmail(customerDTO.getEmail());
        return customerRepository.save(existingCustomer);
    }

    public void deleteByID(Long id) {
        customerRepository.deleteById(id);
    }
}
