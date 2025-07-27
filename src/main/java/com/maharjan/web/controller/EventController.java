package com.maharjan.web.controller;

import com.maharjan.web.dto.Employee;
import com.maharjan.web.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/events")
    public String sendMessage(@RequestBody Employee employee) {
        kafkaProducer.send(employee);
        return "Success";
    }
}
