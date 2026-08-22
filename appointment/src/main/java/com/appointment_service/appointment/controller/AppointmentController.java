package com.appointment_service.appointment.controller;

import com.appointment_service.appointment.business.AppointmentService;
import com.appointment_service.appointment.business.record.in.AppointmentCreateRequest;
import com.appointment_service.appointment.business.record.in.AppointmentUpdateRequest;
import com.appointment_service.appointment.business.record.in.AppointmentUpdateStatus;
import com.appointment_service.appointment.business.record.out.AppointmentDetailsResponse;
import com.appointment_service.appointment.business.record.out.AppointmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponse> addAppointment(
            @RequestBody AppointmentCreateRequest request
    ) {
        return ResponseEntity.ok(appointmentService.addAppointment(request));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDetailsResponse>> findAllAppointments() {
        return ResponseEntity.ok(appointmentService.findAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailsResponse> findAppointmentById(
            @PathVariable UUID id
    ) {
        return  ResponseEntity.ok(appointmentService.findAppointmentById(id));
    }

    @GetMapping("/today")
    public ResponseEntity<List<AppointmentDetailsResponse>> findAppointmentsToday(){
        return ResponseEntity.ok(appointmentService.findAppointmentsToday());
    }

    @GetMapping("/confirmed")
    public ResponseEntity<List<AppointmentDetailsResponse>> findAppointmentsConfirmed(
            @RequestParam LocalDate date
    ){
        return ResponseEntity.ok(appointmentService.findAppointmentsConfirmed(date));
    }

    @GetMapping("/completed")
    public ResponseEntity<List<AppointmentDetailsResponse>> findAppointmentsCompleted(
            @RequestParam LocalDate date
            ){
        return ResponseEntity.ok(appointmentService.findAppointmentsCompleted(date));
    }

    @GetMapping("/canceled")
    public ResponseEntity<List<AppointmentDetailsResponse>> findAppointmentsCanceled(
            @RequestParam LocalDate date
    ){
        return ResponseEntity.ok(appointmentService.findAppointmentsCanceled(date));
    }

    @GetMapping("/reminds")
    public ResponseEntity<List<AppointmentDetailsResponse>> findAppointmentsReminds() {
        return ResponseEntity.ok(appointmentService.findAppointmentsReminds());
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<AppointmentDetailsResponse> findAppointmentByCustomerPhone(
            @PathVariable String phone
    ) {
        return ResponseEntity.ok(appointmentService.findAppointmentByCustomerPhone(phone));
    }

    @GetMapping("/month")
    public ResponseEntity<List<AppointmentDetailsResponse>> findAppointmentsByMonth(
            @RequestParam LocalDate date
    ){
        return ResponseEntity.ok(appointmentService.findAppointmentByMonth(date));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppointmentDetailsResponse> updateAppointment (
            @PathVariable UUID id,
            @RequestBody AppointmentUpdateRequest request
    ) {
       return ResponseEntity.ok(appointmentService.updateAppointment(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentDetailsResponse> updateAppointmentStatus(
            @PathVariable UUID id,
            @RequestBody AppointmentUpdateStatus request
    ) {
        return ResponseEntity.ok(appointmentService.updateAppointmentStatus(id, request.status()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentDetailsResponse> deleteAppointment(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<Void> deleteAllAppointmentsByCustomerId(
            @PathVariable UUID customerId
    ) {
        appointmentService.deleteAllAppointmentsByCustomerId(customerId);

        return ResponseEntity.noContent().build();
    }

}
