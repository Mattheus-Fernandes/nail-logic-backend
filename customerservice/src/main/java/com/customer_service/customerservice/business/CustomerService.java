package com.customer_service.customerservice.infrastructure.business;

import com.customer_service.customerservice.infrastructure.business.mapper.CustomerMapper;
import com.customer_service.customerservice.infrastructure.business.record.in.CustomerCreateRequest;
import com.customer_service.customerservice.infrastructure.business.record.out.CustomerResponse;
import com.customer_service.customerservice.infrastructure.entity.Customer;
import com.customer_service.customerservice.infrastructure.repository.CustomerRepository;
import com.customer_service.customerservice.infrastructure.validators.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerValidator customerValidator;

    public CustomerResponse addCustomer(CustomerCreateRequest request) {

       customerValidator.validateCreate(request);

        CustomerCreateRequest data = new CustomerCreateRequest(
                request.name(),
                request.lastname(),
                request.email(),
                request.phone(),
                request.observation()
        );

        Customer customer = customerMapper.toEntity(data);

        return customerMapper.toResponse(customerRepository.save(customer));

    }
}
