package com.appointment_service.appointment.business;

import com.appointment_service.appointment.business.mapper.AppointmentMapper;
import com.appointment_service.appointment.business.record.in.AppointmentCreateRequest;
import com.appointment_service.appointment.business.record.in.AppointmentUpdateRequest;
import com.appointment_service.appointment.business.record.out.AppointmentResponse;
import com.appointment_service.appointment.infrastructure.entity.Appointment;
import com.appointment_service.appointment.infrastructure.enums.AppointmentStatus;
import com.appointment_service.appointment.infrastructure.repository.AppointmentRepository;
import com.appointment_service.appointment.infrastructure.validators.AppointmentValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    public List<AppointmentResponse> findAllAppointments() {

        List<Appointment> appointmentList = appointmentRepository.findAll();

        return appointmentMapper.toResponseList(appointmentList);

    }

    public AppointmentResponse findAppointmentById(UUID id) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado")
        );

        return appointmentMapper.toResponse(appointment);
    }

    public List<AppointmentResponse> findAppointmentsToday() {
        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDateAndStatus(LocalDate.now(), AppointmentStatus.CONFIRMED);

        return appointmentMapper.toResponseList(appointmentList);

    }

    public List<AppointmentResponse> findAppointmentsConfirmed(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDateBetweenAndStatus(startDate, endDate, AppointmentStatus.CONFIRMED);


        return appointmentMapper.toResponseList(appointmentList);
    }

    public List<AppointmentResponse> findAppointmentsCompleted(int year, int month) {

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDateBetweenAndStatus(startDate, endDate, AppointmentStatus.COMPLETED);

        return appointmentMapper.toResponseList(appointmentList);
    }

    public List<AppointmentResponse> findAppointmentsCanceled(int year, int month) {

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDateBetweenAndStatus(startDate, endDate, AppointmentStatus.CANCELED);

        return appointmentMapper.toResponseList(appointmentList);
    }

    public AppointmentResponse updateAppointmentStatus(UUID id, AppointmentStatus status) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado")
        );

        appointment.setStatus(status);

        return appointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    public AppointmentResponse updateAppointment(UUID id, AppointmentUpdateRequest request) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado")
        );

        appointmentMapper.updateFromRequest(request, appointment);

        appointmentValidator.validateUpdate(appointment);

        appointment.setUpdatedAt(LocalDateTime.now());

        return appointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    public AppointmentResponse deleteAppointment(UUID id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado")
        );

        appointmentRepository.deleteById(id);

        return appointmentMapper.toResponse(appointment);
    }

    public void deleteAllAppointmentsByCustomerId(UUID customerId) {
        List<Appointment> appointmentList = appointmentRepository.findByCustomerId(customerId);

        if(appointmentList.isEmpty()) {
            throw new RuntimeException("Nenhum agendamento encontrado");
        }

        appointmentRepository.deleteAllByCustomerId(customerId);
    }
}
