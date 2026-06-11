package com.appointment_service.appointment.business.record.out;

import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String name,
        String lastname,
        String email,
        String phone,
        boolean active,
        String observation
) {
}
