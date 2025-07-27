package com.maharjan.web.producer;

import com.maharjan.web.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    @Value("${topic.name}")
    private String topicName;

    public void send(Employee employee) {
        CompletableFuture<SendResult<String, Employee>> future = kafkaTemplate.send(topicName, employee);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + employee + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + employee + "] due to :" + ex.getMessage());
            }
        });
    }
}
