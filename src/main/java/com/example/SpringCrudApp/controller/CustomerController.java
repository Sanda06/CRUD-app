package com.example.SpringCrudApp.controller;


import com.example.SpringCrudApp.dto.CustomerDTO;
import com.example.SpringCrudApp.entity.Customer;
import com.example.SpringCrudApp.service.CustomerService;
import com.example.SpringCrudApp.util.MapperUtil;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCountries() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) {
        return MapperUtil.map(customerService.getCustomer(id), CustomerDTO.class);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        var customer = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok().body(MapperUtil.map(customer, CustomerDTO.class));
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO) {
        return MapperUtil.map(customerService.updateCustomer(id, customerDTO), CustomerDTO.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }
}
