package com.appointment_service.appointment.business.record.in;

import java.time.LocalDate;

public record MonthPeriod(
        LocalDate firstDay,
        LocalDate lastDay
) {
}
