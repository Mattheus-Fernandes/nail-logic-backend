package com.appointment_service.appointment.infrastructure.repository;

import com.appointment_service.appointment.infrastructure.DAO.AppointmentDAO;
import com.appointment_service.appointment.infrastructure.entity.Appointment;
import com.appointment_service.appointment.infrastructure.enums.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AppointmentReposit {

    private final AppointmentDAO appointmentDAO;

    public List<Appointment> findAllAppointments() {
        return this.appointmentDAO.findAll();
    }

    public Optional<Appointment> findAppointmentById(UUID id) {
        return this.appointmentDAO.findAppointmentById(id);
    }

    public List<Appointment> findAllAppointmentsForReminds(LocalDate appointmentDate) {
        return this.appointmentDAO.findAllAppointmentsReminds(appointmentDate);
    }

    public Optional<Appointment> findAppointmentByCustomerId(UUID customerId) {
        return this.appointmentDAO.findAppointmentByCustomerId(customerId);
    }

    public List<Appointment> findAllAppointmentsToday(LocalDate appointmentDate) {
        return this.appointmentDAO.findAllAppointmentsToday(appointmentDate);
    }

    public List<Appointment> findAllAppointmentsByStatus(AppointmentStatus status, LocalDate startAppointmentDate, LocalDate endAppointmentDate) {
        return this.appointmentDAO.findAllAppointmentsByStatus(status, startAppointmentDate, endAppointmentDate);
    }

    public List<Appointment> filterAppointmentByMonth(LocalDate startAppointmentDate, LocalDate endAppointmentDate) {
        return this.appointmentDAO.filterAppointmentByMonth(startAppointmentDate, endAppointmentDate);
    }
}
