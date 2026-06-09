package com.appointment_service.appointment.controller;

import com.appointment_service.appointment.business.AppointmentService;
import com.appointment_service.appointment.business.record.in.AppointmentCreateRequest;
import com.appointment_service.appointment.business.record.out.AppointmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponse> addAppointment(
            @RequestBody AppointmentCreateRequest request
    ) {
        return ResponseEntity.ok(appointmentService.addAppointment(request));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> findAllAppointments() {
        return ResponseEntity.ok(appointmentService.findAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> findAppointmentById(
            @PathVariable UUID id
    ) {
        return  ResponseEntity.ok(appointmentService.findAppointmentById(id));
    }

    @GetMapping("/today")
    public ResponseEntity<List<AppointmentResponse>> findAppointmentsToday(){
        return ResponseEntity.ok(appointmentService.findAppointmentsToday());
    }

    @GetMapping("/confirmed")
    public ResponseEntity<List<AppointmentResponse>> findAppointmentsConfirmed(
            @RequestParam int year,
            @RequestParam int month
    ){
        return ResponseEntity.ok(appointmentService.findAppointmentsConfirmed(year, month));
    }

    @GetMapping("/completed")
    public ResponseEntity<List<AppointmentResponse>> findAppointmentsCompleted(
            @RequestParam int year,
            @RequestParam int month
    ){
        return ResponseEntity.ok(appointmentService.findAppointmentsCompleted(year, month));
    }

    @GetMapping("/canceled")
    public ResponseEntity<List<AppointmentResponse>> findAppointmentsCanceled(
            @RequestParam int year,
            @RequestParam int month
    ){
        return ResponseEntity.ok(appointmentService.findAppointmentsCanceled(year, month));
    }

}
