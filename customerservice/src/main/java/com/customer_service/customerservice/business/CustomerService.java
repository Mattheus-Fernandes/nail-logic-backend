package com.customer_service.customerservice.business;

import com.customer_service.customerservice.business.mapper.CustomerMapper;
import com.customer_service.customerservice.business.record.in.CustomerCreateRequest;
import com.customer_service.customerservice.business.record.out.CustomerResponse;
import com.customer_service.customerservice.infrastructure.entity.Customer;
import com.customer_service.customerservice.infrastructure.repository.CustomerRepository;
import com.customer_service.customerservice.infrastructure.validators.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public List<CustomerResponse> findAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();

        return customerMapper.toResponseList(customerList);
    }

    public CustomerResponse findById(UUID id) {

        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente não encontrada")
        );

        return customerMapper.toResponse(customer);
    }

    public List<CustomerResponse> findByName(String name) {
        List<Customer> customersList = customerRepository.findByNameContainingIgnoreCase(name);

        return customerMapper.toResponseList(customersList);
    }

    public CustomerResponse findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Cliente não encontrada")
        );

        return customerMapper.toResponse(customer);
    }

}
