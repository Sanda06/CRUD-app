package com.example.SpringCrudApp.repository;


import com.example.SpringCrudApp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
