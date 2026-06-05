package com.customer_service.customerservice.infrastructure.repository;

import com.customer_service.customerservice.infrastructure.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    boolean existsByNameIgnoreCaseAndLastnameIgnoreCase(String name, String lastname);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    List<Customer> findByNameContainingIgnoreCase(String name);
}
