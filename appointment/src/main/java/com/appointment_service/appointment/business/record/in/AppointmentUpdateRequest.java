package com.appointment_service.appointment.business.record.in;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AppointmentUpdateRequest(
        UUID id,
        UUID customerId,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        String serviceName,
        String observation
) {
}
