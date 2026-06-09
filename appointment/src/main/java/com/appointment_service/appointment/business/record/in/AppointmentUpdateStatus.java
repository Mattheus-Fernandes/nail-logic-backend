package com.appointment_service.appointment.business.record.in;

import com.appointment_service.appointment.infrastructure.enums.AppointmentStatus;

public record AppointmentUpdateStatus(
        AppointmentStatus status
) {
}
