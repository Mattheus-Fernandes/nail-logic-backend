package com.appointment_service.appointment.business.mapper;

import com.appointment_service.appointment.business.record.in.AppointmentCreateRequest;
import com.appointment_service.appointment.business.record.in.AppointmentUpdateRequest;
import com.appointment_service.appointment.business.record.out.AppointmentDetailsResponse;
import com.appointment_service.appointment.business.record.out.AppointmentResponse;
import com.appointment_service.appointment.business.record.out.CustomerResponse;
import com.appointment_service.appointment.infrastructure.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppointmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "SCHEDULED")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Appointment toEntity(AppointmentCreateRequest request);

    AppointmentResponse toResponse(Appointment appointment);

    List<AppointmentResponse> toResponseList(List<Appointment> appointmentList);

    @Mapping(target = "id", source = "appointment.id")
    @Mapping(target = "observation", source = "appointment.observation")
    @Mapping(target = "customer", source = "customerResponse")
    AppointmentDetailsResponse toDetailsResponse(Appointment appointment, CustomerResponse customerResponse);

    void updateFromRequest(AppointmentUpdateRequest request, @MappingTarget Appointment appointment);
}
