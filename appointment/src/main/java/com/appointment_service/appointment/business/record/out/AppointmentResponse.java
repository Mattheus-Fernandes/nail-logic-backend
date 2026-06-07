package com.appointment_service.appointment.business.record.out;

import com.appointment_service.appointment.infrastructure.enums.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID id,
        UUID customerId,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        String serviceName,
        AppointmentStatus status,
        String observation,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
