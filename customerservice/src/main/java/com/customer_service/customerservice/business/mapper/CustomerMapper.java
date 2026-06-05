package com.customer_service.customerservice.infrastructure.business.mapper;

import com.customer_service.customerservice.infrastructure.business.record.in.*;
import com.customer_service.customerservice.infrastructure.business.record.out.CustomerResponse;
import com.customer_service.customerservice.infrastructure.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerCreateRequest request);

    CustomerResponse toResponse(Customer customer);

    List<CustomerResponse> toResponseList(List<Customer> customers);

    void updateEntity(
            CustomerUpdateRequest request,
            @MappingTarget Customer customer
    );

    void updateEmail(
            CustomerUpdateEmailRequest request,
            @MappingTarget Customer customer
    );

    void updatePhone(
            CustomerUpdatePhoneRequest request,
            @MappingTarget Customer customer
    );

    void updateActive(
            CustomerUpdateActiveRequest request,
            @MappingTarget Customer customer
    );
}
