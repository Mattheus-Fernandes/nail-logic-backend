package com.appointment_service.appointment.infrastructure.repository;

import com.appointment_service.appointment.infrastructure.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    boolean existsByAppointmentDateAndAppointmentTime(LocalDate appointmentDate, LocalTime appointmentTime);
}
