package com.maharjan.web.consumer;

import com.maharjan.web.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "${topic.name}")
    public void consume(ConsumerRecord<String, Employee> consumerRecord) {
        String key = consumerRecord.key();
        Employee value = consumerRecord.value();
        log.info(key + " : " + value.toString());
    }
}
