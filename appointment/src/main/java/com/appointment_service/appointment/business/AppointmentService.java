package com.appointment_service.appointment.business;

import com.appointment_service.appointment.business.mapper.AppointmentMapper;
import com.appointment_service.appointment.business.record.in.AppointmentCreateRequest;
import com.appointment_service.appointment.business.record.out.AppointmentResponse;
import com.appointment_service.appointment.infrastructure.entity.Appointment;
import com.appointment_service.appointment.infrastructure.repository.AppointmentRepository;
import com.appointment_service.appointment.infrastructure.validators.AppointmentValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final AppointmentValidator appointmentValidator;

    public AppointmentResponse addAppointment(AppointmentCreateRequest request) {

        appointmentValidator.validateCreate(request);

        Appointment appointment = appointmentMapper.toEntity(request);

        return appointmentMapper.toResponse(appointmentRepository.save(appointment));

    }
}
