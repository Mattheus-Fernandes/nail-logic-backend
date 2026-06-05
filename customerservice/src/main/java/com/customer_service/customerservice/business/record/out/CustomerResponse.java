package com.customer_service.customerservice.business.record.out;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String name,
        String lastname,
        String email,
        String phone,
        boolean active,
        String observation,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
