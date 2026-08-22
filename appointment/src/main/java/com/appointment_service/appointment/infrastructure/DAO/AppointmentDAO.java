package com.appointment_service.appointment.infrastructure.DAO;

import com.appointment_service.appointment.infrastructure.entity.Appointment;
import com.appointment_service.appointment.infrastructure.enums.AppointmentStatus;
import com.appointment_service.appointment.infrastructure.query.AppointmentQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AppointmentDAO {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Appointment> findAll() {
        return entityManager
                .createNamedQuery(AppointmentQueries.FIND_ALL_APPOINTMENTS, Appointment.class)
                .getResultList();
    }

    public Optional<Appointment> findAppointmentById(UUID id) {
        List<Appointment> appointmentList = entityManager
                .createNamedQuery(AppointmentQueries.FIND_APPOINTMENT_BY_ID, Appointment.class)
                .setParameter("id", id)
                .getResultList();

        return appointmentList.stream().findFirst();
    }

    public List<Appointment> findAllAppointmentsReminds(LocalDate appointmentDate) {
        return entityManager
                .createNamedQuery(AppointmentQueries.FIND_ALL_APPOINTMENTS_REMINDS, Appointment.class)
                .setParameter("appointment_date", appointmentDate)
                .getResultList();
    }

    public Optional<Appointment> findAppointmentByCustomerId(UUID customerId) {
        List<Appointment> appointmentList = entityManager
                .createNamedQuery(AppointmentQueries.FIND_APPOINTMENT_BY_CUSTOMER_ID, Appointment.class)
                .setParameter("customer_id", customerId)
                .getResultList();

        return appointmentList.stream().findFirst();
    }

    public List<Appointment> findAllAppointmentsToday(LocalDate appointmentDate) {
        return entityManager
                .createNamedQuery(AppointmentQueries.FIND_ALL_APPOINTMENTS_TODAY, Appointment.class)
                .setParameter("appointment_date", appointmentDate)
                .getResultList();
    }

    public List<Appointment> findAllAppointmentsByStatus(AppointmentStatus status, LocalDate startAppointmentDate, LocalDate endAppointmentDate) {

        switch (status) {
            case CONFIRMED:
                return entityManager
                        .createNamedQuery(AppointmentQueries.FIND_ALL_APPOINTMENTS_CONFIRMED, Appointment.class)
                        .setParameter("start_appointment_date", startAppointmentDate)
                        .setParameter("end_appointment_date", endAppointmentDate)
                        .getResultList();

            case COMPLETED:
                return entityManager
                        .createNamedQuery(AppointmentQueries.FIND_ALL_APPOINTMENTS_COMPLETED, Appointment.class)
                        .setParameter("start_appointment_date", startAppointmentDate)
                        .setParameter("end_appointment_date", endAppointmentDate)
                        .getResultList();

            case CANCELED:
                return entityManager
                        .createNamedQuery(AppointmentQueries.FIND_ALL_APPOINTMENTS_CANCELED, Appointment.class)
                        .setParameter("start_appointment_date", startAppointmentDate)
                        .setParameter("end_appointment_date", endAppointmentDate)
                        .getResultList();
            default:
                throw new RuntimeException("Status desconhecido " + status);
        }
    }

    public List<Appointment> filterAppointmentByMonth( LocalDate startAppointmentDate, LocalDate endAppointmentDate) {
        return entityManager
                .createNamedQuery(AppointmentQueries.FILTER_APPOINTMENTS_BY_MONTH, Appointment.class)
                .setParameter("start_appointment_date", startAppointmentDate)
                .setParameter("end_appointment_date", endAppointmentDate)
                .getResultList();
    }

    public List<Appointment> filterAppointmentByCustomerId(UUID customerId) {
        return entityManager
                .createNamedQuery(AppointmentQueries.FILTER_ALL_APPOINTMENTS_BY_CUSTOMER_ID, Appointment.class)
                .setParameter("customer_id", customerId)
                .getResultList();
    }
}
