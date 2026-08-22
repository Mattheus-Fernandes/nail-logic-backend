package com.appointment_service.appointment.infrastructure.entity;

import com.appointment_service.appointment.infrastructure.enums.AppointmentStatus;
import com.appointment_service.appointment.infrastructure.query.AppointmentQueries;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@NamedNativeQueries({
        @NamedNativeQuery(
               name = AppointmentQueries.FIND_ALL_APPOINTMENTS,
               query = AppointmentQueries.FIND_ALL_APPOINTMENTS_QUERY,
               resultClass = Appointment.class
        ),
        @NamedNativeQuery(
                name = AppointmentQueries.FIND_APPOINTMENT_BY_ID,
                query = AppointmentQueries.FIND_APPOINTMENT_BY_ID_QUERY,
                resultClass = Appointment.class
        ),
        @NamedNativeQuery(
                name = AppointmentQueries.FIND_ALL_APPOINTMENTS_REMINDS,
                query = AppointmentQueries.FIND_ALL_APPOINTMENTS_REMINDS_QUERY,
                resultClass = Appointment.class
        ),
        @NamedNativeQuery(
                name = AppointmentQueries.FIND_APPOINTMENT_BY_CUSTOMER_ID,
                query = AppointmentQueries.FIND_APPOINTMENT_BY_CUSTOMER_ID_QUERY,
                resultClass = Appointment.class
        ),
        @NamedNativeQuery(
                name = AppointmentQueries.FIND_ALL_APPOINTMENTS_TODAY,
                query = AppointmentQueries.FIND_ALL_APPOINTMENTS_TODAY_QUERY,
                resultClass = Appointment.class
        ),
        @NamedNativeQuery(
                name = AppointmentQueries.FIND_ALL_APPOINTMENTS_CONFIRMED,
                query = AppointmentQueries.FIND_ALL_APPOINTMENTS_CONFIRMED_QUERY,
                resultClass = Appointment.class
        ),
        @NamedNativeQuery(
                name = AppointmentQueries.FIND_ALL_APPOINTMENTS_COMPLETED,
                query = AppointmentQueries.FIND_ALL_APPOINTMENTS_COMPLETED_QUERY,
                resultClass = Appointment.class
        ),
        @NamedNativeQuery(
                name = AppointmentQueries.FIND_ALL_APPOINTMENTS_CANCELED,
                query = AppointmentQueries.FIND_ALL_APPOINTMENTS_CANCELED_QUERY,
                resultClass = Appointment.class
        ),
        @NamedNativeQuery(
                name = AppointmentQueries.FILTER_APPOINTMENTS_BY_MONTH,
                query = AppointmentQueries.FILTER_APPOINTMENTS_BY_MONTH_QUERY,
                resultClass = Appointment.class
        )
})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "customer_id")
    private UUID customerId;

    @Column(nullable = false, name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(nullable = false, name = "appointment_time")
    private LocalTime appointmentTime;

    @Column(nullable = false, name = "service_name")
    private String serviceName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private AppointmentStatus status;

    @Column(name = "observation")
    private String observation;

    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
