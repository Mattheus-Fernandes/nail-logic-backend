package com.appointment_service.appointment.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${N8N_WEBHOOK_CONFIRM}")
    private String webhookUrl;

    public void sendAppointmentCreated(
            String customerName,
            String customerLastname,
            String phone,
            String date,
            String time,
            String serviceName
    ){
        Map<String, Object> body = new HashMap<>();
        body.put("customerName", customerName);
        body.put("customerLastname", customerLastname);
        body.put("phone", phone);
        body.put("date", date);
        body.put("time", time);
        body.put("serviceName", serviceName);

        restTemplate.postForObject(
                webhookUrl,
                body,
                String.class
        );
    }
}
