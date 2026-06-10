package com.appointment_service.appointment.infrastructure.validators;

import com.appointment_service.appointment.business.record.in.AppointmentCreateRequest;
import com.appointment_service.appointment.infrastructure.entity.Appointment;
import com.appointment_service.appointment.infrastructure.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class AppointmentValidator {

    private final AppointmentRepository appointmentRepository;

    public void validateCreate(AppointmentCreateRequest request) {
        validateDate(request.appointmentDate());

        boolean exist = appointmentRepository.existsByAppointmentDateAndAppointmentTime(
                request.appointmentDate(), request.appointmentTime()
        );

        errorDateAndTime(exist);
    }

    public void  validateUpdate(Appointment appointment) {
        validateDate(appointment.getAppointmentDate());

        boolean exist = appointmentRepository.existsByAppointmentDateAndAppointmentTimeAndIdNot(
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getId()
        );

        errorDateAndTime(exist);
    }

    private void validateDate(LocalDate date) {

        if(date == null) {
            throw new RuntimeException("A data é obrigatória");

        }

        if(date.isBefore(LocalDate.now())) {
            throw new RuntimeException("Não é possível agendar para uma data passada");
        }
    }

    private void errorDateAndTime(boolean appointmentDate) {

        if(appointmentDate) {
            throw new RuntimeException("Horário já ocupado");
        }
    }
}
