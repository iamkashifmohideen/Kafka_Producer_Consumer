package com.igmi.spring.kafka.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "Test",
                    groupId = "group_id")
    public void consume(String message){
        LOGGER.info(String.format("Message received -> %s", message));
    }
}