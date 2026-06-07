package com.customer_service.customerservice.controller;

import com.customer_service.customerservice.business.CustomerService;
import com.customer_service.customerservice.business.record.in.CustomerCreateRequest;
import com.customer_service.customerservice.business.record.in.CustomerUpdateActiveRequest;
import com.customer_service.customerservice.business.record.in.CustomerUpdateRequest;
import com.customer_service.customerservice.business.record.out.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(
            @RequestBody CustomerCreateRequest request
    ){
        return ResponseEntity.ok(customerService.addCustomer(request));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<CustomerResponse>> findByName(
            @RequestParam String name
    ) {
        return ResponseEntity.ok(customerService.findByName(name));
    }

    @GetMapping("/email")
    public ResponseEntity<CustomerResponse> findByEmail(
            @RequestParam String email
    ) {
        return ResponseEntity.ok(customerService.findByEmail(email));
    }

    @GetMapping("/phone")
    public ResponseEntity<CustomerResponse> findByPhone(
            @RequestParam String phone
    ) {
        return ResponseEntity.ok(customerService.findByPhone(phone));
    }

    @GetMapping("/active")
    public ResponseEntity<List<CustomerResponse>> findByActive(
            @RequestParam boolean active
    ) {
        return ResponseEntity.ok((customerService.findByActive(active)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> editCustomer(
            @PathVariable UUID id,
            @RequestBody CustomerUpdateRequest request
    ) {
        return ResponseEntity.ok(customerService.editCustomer(id, request));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<CustomerResponse> editActiveCustomer(
            @PathVariable UUID id,
            @RequestBody CustomerUpdateActiveRequest request
    ) {
        return ResponseEntity.ok(customerService.editActiveCustomer(id, request));
    }

}
