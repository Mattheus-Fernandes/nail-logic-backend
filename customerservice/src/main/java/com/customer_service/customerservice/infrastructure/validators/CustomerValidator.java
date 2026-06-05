package com.customer_service.customerservice.infrastructure.validators;

import com.customer_service.customerservice.infrastructure.business.record.in.CustomerCreateRequest;
import com.customer_service.customerservice.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerValidator {

    private final CustomerRepository customerRepository;

    public void validateCreate(CustomerCreateRequest request) {
        validateNameAndLastname(request);
        validateEmail(request);
        validatePhone(request);
    }

    private void validateNameAndLastname(CustomerCreateRequest request) {

        boolean exist = customerRepository.existsByNameIgnoreCaseAndLastnameIgnoreCase(
                request.name(), request.lastname()
        );

        if((request.name() != null || request.lastname() != null) && exist) {
            throw new RuntimeException("Cliente já cadastrada");
        }
    }

    private void validateEmail(CustomerCreateRequest request) {

        boolean exist = customerRepository.existsByEmail(request.email());

        if(request.email() != null && exist) {
            throw new RuntimeException("E-mail já existente");
        }

    }

    private void validatePhone(CustomerCreateRequest request) {
        boolean exist = customerRepository.existsByPhone(request.phone());

        if(request.phone() != null && exist) {
            throw new RuntimeException("Celular já castrado");
        }
    }
}
