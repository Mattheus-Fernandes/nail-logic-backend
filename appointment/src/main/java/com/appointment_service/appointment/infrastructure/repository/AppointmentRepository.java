package com.appointment_service.appointment.infrastructure.repository;

import com.appointment_service.appointment.infrastructure.entity.Appointment;
import com.appointment_service.appointment.infrastructure.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    boolean existsByAppointmentDateAndAppointmentTime(LocalDate appointmentDate, LocalTime appointmentTime);
    boolean existsByAppointmentDateAndAppointmentTimeAndIdNot(LocalDate appointmentDate, LocalTime appointmentTime, UUID id);
    List<Appointment> findByAppointmentDateBetweenAndStatus(LocalDate startDate, LocalDate endDate, AppointmentStatus status);
    List<Appointment> findByAppointmentDateAndStatus(LocalDate appointmentDate, AppointmentStatus status);
}
