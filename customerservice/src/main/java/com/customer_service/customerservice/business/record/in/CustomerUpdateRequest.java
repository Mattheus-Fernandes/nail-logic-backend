package com.customer_service.customerservice.business.record.in;

public record CustomerUpdateRequest(
        String name,
        String lastname,
        String email,
        String phone,
        String observation,
        boolean active
) {
}
