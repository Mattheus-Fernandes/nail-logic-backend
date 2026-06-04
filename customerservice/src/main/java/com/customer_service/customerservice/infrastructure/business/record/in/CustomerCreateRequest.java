package com.customer_service.customerservice.infrastructure.business.record.in;

public record CustomerCreateRequest(
    String name,
    String lastname,
    String email,
    String phone,
    String observation
) {
}
