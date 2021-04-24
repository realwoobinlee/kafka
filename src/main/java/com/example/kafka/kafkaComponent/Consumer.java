package com.example.kafka.kafkaComponent;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "test-topic", groupId = "some_group1")
    public void consume(String message) throws IOException, InterruptedException {
        someWorkload();
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }

    private void someWorkload() throws InterruptedException {
        long millis = 2000;
        Thread.sleep(millis);
    }
}
