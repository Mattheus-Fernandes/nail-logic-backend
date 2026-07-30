package com.appointment_service.appointment.business;

import com.appointment_service.appointment.business.mapper.AppointmentMapper;
import com.appointment_service.appointment.business.record.in.AppointmentCreateRequest;
import com.appointment_service.appointment.business.record.in.AppointmentUpdateRequest;
import com.appointment_service.appointment.business.record.out.AppointmentDetailsResponse;
import com.appointment_service.appointment.business.record.out.AppointmentResponse;
import com.appointment_service.appointment.business.record.out.CustomerResponse;
import com.appointment_service.appointment.infrastructure.client.CustomerClient;
import com.appointment_service.appointment.infrastructure.entity.Appointment;
import com.appointment_service.appointment.infrastructure.enums.AppointmentStatus;
import com.appointment_service.appointment.infrastructure.repository.AppointmentRepository;
import com.appointment_service.appointment.infrastructure.validators.AppointmentValidator;

import jakarta.transaction.Transactional;
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
    private final CustomerClient customerClient;
    private final NotificationService notificationService;

    public AppointmentResponse addAppointment(AppointmentCreateRequest request) {

        appointmentValidator.validateCreate(request);

        Appointment appointment = appointmentMapper.toEntity(request);

        CustomerResponse customer = customerClient.findById(appointment.getCustomerId());

        notificationService.sendAppointmentCreated(
                customer.name(),
                customer.lastname(),
                customer.phone(),
                request.appointmentDate().toString(),
                request.appointmentTime().toString(),
                request.serviceName()
        );

        return appointmentMapper.toResponse(appointmentRepository.save(appointment));

    }

    public List<AppointmentDetailsResponse> findAllAppointments() {

        List<Appointment> appointmentList = appointmentRepository.findAll();

        return appointmentList.stream()
                .map(appointment -> {

                    CustomerResponse customer = customerClient.findById(appointment.getCustomerId());

                    return appointmentMapper.toDetailsResponse(appointment, customer);

                })
                .toList();

    }

    public AppointmentDetailsResponse findAppointmentById(UUID id) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado")
        );

        CustomerResponse customer = customerClient.findById(appointment.getCustomerId());

        return appointmentMapper.toDetailsResponse(appointment, customer);
    }

    public List<AppointmentDetailsResponse> findAppointmentsToday() {
        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDateAndStatus(LocalDate.now(), AppointmentStatus.CONFIRMED);

        return appointmentList.stream()
                .map(appointment -> {
                    CustomerResponse customer = customerClient.findById(appointment.getCustomerId());

                    return appointmentMapper.toDetailsResponse(appointment, customer);
                })
                .toList();
    }

    public List<AppointmentDetailsResponse> findAppointmentsConfirmed(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDateBetweenAndStatus(startDate, endDate, AppointmentStatus.CONFIRMED);

        return appointmentList.stream()
                .map(appointment -> {
                    CustomerResponse customer = customerClient.findById(appointment.getCustomerId());

                    return appointmentMapper.toDetailsResponse(appointment, customer);
                })
                .toList();
    }

    public List<AppointmentDetailsResponse> findAppointmentsCompleted(int year, int month) {

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDateBetweenAndStatus(startDate, endDate, AppointmentStatus.COMPLETED);

        return appointmentList.stream()
                .map(appointment -> {
                    CustomerResponse customer = customerClient.findById(appointment.getCustomerId());

                    return appointmentMapper.toDetailsResponse(appointment, customer);
                })
                .toList();

    }

    public List<AppointmentDetailsResponse> findAppointmentsCanceled(int year, int month) {

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDateBetweenAndStatus(startDate, endDate, AppointmentStatus.CANCELED);

        return appointmentList.stream()
                .map(appointment -> {
                    CustomerResponse customer = customerClient.findById(appointment.getCustomerId());

                    return appointmentMapper.toDetailsResponse(appointment, customer);
                })
                .toList();
    }

    public List<AppointmentDetailsResponse> findAppointmentsReminds() {

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDate(tomorrow);

        return appointmentList.stream()
                .map(appointment -> {

                    CustomerResponse customer = customerClient.findById(appointment.getCustomerId());

                    return appointmentMapper.toDetailsResponse(appointment, customer);

                })
                .toList();
    }

    public AppointmentDetailsResponse updateAppointmentStatus(UUID id, AppointmentStatus status) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado")
        );

        CustomerResponse customer = customerClient.findById(appointment.getCustomerId());

        appointment.setStatus(status);

        return appointmentMapper.toDetailsResponse(appointment, customer);
    }

    public AppointmentDetailsResponse updateAppointment(UUID id, AppointmentUpdateRequest request) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado")
        );

        CustomerResponse customer = customerClient.findById(appointment.getCustomerId());

        appointmentMapper.updateFromRequest(request, appointment);

        appointmentValidator.validateUpdate(appointment);

        appointment.setUpdatedAt(LocalDateTime.now());

        return appointmentMapper.toDetailsResponse(appointment, customer);

    }

    public AppointmentDetailsResponse deleteAppointment(UUID id) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado")
        );

        CustomerResponse customer = customerClient.findById(appointment.getCustomerId());


        appointmentRepository.deleteById(id);

        return appointmentMapper.toDetailsResponse(appointment, customer);

    }

    @Transactional
    public void deleteAllAppointmentsByCustomerId(UUID customerId) {
        List<Appointment> appointmentList = appointmentRepository.findByCustomerId(customerId);

        if(appointmentList.isEmpty()) {
            throw new RuntimeException("Nenhum agendamento encontrado");
        }

        appointmentRepository.deleteAllByCustomerId(customerId);
    }
}
