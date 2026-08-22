package com.customer_service.customerservice.business.mapper;

import com.customer_service.customerservice.business.record.in.*;
import com.customer_service.customerservice.business.record.out.CustomerResponse;
import com.customer_service.customerservice.infrastructure.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    Customer toEntity(CustomerCreateRequest request);

    CustomerResponse toResponse(Customer customer);

    List<CustomerResponse> toResponseList(List<Customer> customers);

    Customer updateEntity(
            CustomerUpdateRequest request,
            @MappingTarget Customer customer
    );

    Customer updateActive(
            CustomerUpdateActiveRequest request,
            @MappingTarget Customer customer
    );
}
