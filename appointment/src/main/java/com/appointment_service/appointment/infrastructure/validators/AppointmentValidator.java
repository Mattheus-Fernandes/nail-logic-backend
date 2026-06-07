package com.appointment_service.appointment.infrastructure.validators;

import com.appointment_service.appointment.business.record.in.AppointmentCreateRequest;
import com.appointment_service.appointment.infrastructure.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AppointmentValidator {

    private final AppointmentRepository appointmentRepository;

    public void validateCreate(AppointmentCreateRequest request) {
        validateDate(request);
        validateDateAndTime(request);
        validateDateAndTime(request);
    }


    private void validateDate(AppointmentCreateRequest request) {

        if(request.appointmentDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Não é possível agendar para uma data passada");
        }

    }

    private void validateDateAndTime(AppointmentCreateRequest request) {

        boolean exist = appointmentRepository.existByAppointmentDateAndAppointTime(
                request.appointmentDate(), request.appointmentTime()
        );

        if (request.appointmentDate() != null && request.appointmentTime() != null && exist) {
            throw new RuntimeException("Horário já ocupado");
        }

    }
}
