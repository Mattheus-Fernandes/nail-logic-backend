package com.appointment_service.appointment.infrastructure.query;

public class AppointmentQueries {

    private AppointmentQueries(){}

    public static final String FIND_ALL_APPOINTMENTS = "Appointment.findAll";

    public static final String FIND_ALL_APPOINTMENTS_QUERY = """
            SELECT *
            FROM appointments
            ORDER BY appointment_date, appointment_time ASC
            """;

    public static final String FIND_APPOINTMENT_BY_ID = "Appointment.findByCustomerId";

    public static final String FIND_APPOINTMENT_BY_ID_QUERY = """
            SELECT *
            FROM appointments
            WHERE id = :id
            """;

    public static final String FIND_ALL_APPOINTMENTS_REMINDS = "Appointment.findAllReminds";

    public static final String FIND_ALL_APPOINTMENTS_REMINDS_QUERY = """
            SELECT *
            FROM appointments
            WHERE status = 'SCHEDULED'
            AND appointment_date = :appointment_date
            ORDER BY appointment_date, appointment_time ASC
            """;

    public static final String FIND_APPOINTMENT_BY_CUSTOMER_ID = "Appointment.findAppointmentByIdCustomer";

    public static final String FIND_APPOINTMENT_BY_CUSTOMER_ID_QUERY = """
                            SELECT DISTINCT *
                            FROM appointments
                            WHERE customer_id = :customer_id
                            """;
    public static final String FIND_ALL_APPOINTMENTS_TODAY = "Appointment.findAllToday";

    public static final String FIND_ALL_APPOINTMENTS_TODAY_QUERY = """
            SELECT *
            FROM appointments
            WHERE status = 'CONFIRMED'
            AND appointment_date = :appointment_date
            ORDER BY appointment_date, appointment_time ASC
            """;

    public static final String FIND_ALL_APPOINTMENTS_CONFIRMED = "Appointment.findAllConfirmed";

    public static final String FIND_ALL_APPOINTMENTS_CONFIRMED_QUERY = """
            SELECT *
            FROM appointments
            WHERE status = 'CONFIRMED'
            AND appointment_date BETWEEN :start_appointment_date AND :end_appointment_date
            ORDER BY appointment_date, appointment_time ASC
            """;

    public static final String FIND_ALL_APPOINTMENTS_COMPLETED = "Appointment.findAllCompleted";

    public static final String FIND_ALL_APPOINTMENTS_COMPLETED_QUERY = """
            SELECT *
            FROM appointments
            WHERE status = 'COMPLETED'
            AND appointment_date BETWEEN :start_appointment_date AND :end_appointment_date
            ORDER BY appointment_date, appointment_time ASC
            """;

    public static final String FIND_ALL_APPOINTMENTS_CANCELED = "Appointment.findAllCanceled";

    public static final String FIND_ALL_APPOINTMENTS_CANCELED_QUERY = """
            SELECT *
            FROM appointments
            WHERE status = 'CANCELED'
            AND appointment_date BETWEEN :start_appointment_date AND :end_appointment_date
            ORDER BY appointment_date, appointment_time ASC
            """;
}
