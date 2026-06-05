package com.customer_service.customerservice.controller;

import com.customer_service.customerservice.business.CustomerService;
import com.customer_service.customerservice.business.record.in.CustomerCreateRequest;
import com.customer_service.customerservice.business.record.out.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
