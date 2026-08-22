package com.appointment_service.appointment.infrastructure.client;

import com.appointment_service.appointment.business.record.out.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "customer-service", url = "${customer.service.url}")
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    CustomerResponse findById(@PathVariable UUID id);

    @GetMapping("/customers/phone")
    CustomerResponse findByPhone(@RequestParam("phone") String phone);
}
