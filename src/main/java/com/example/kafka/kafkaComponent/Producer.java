package com.example.kafka.kafkaComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class Producer {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Value("${test.topic}")
    String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        ListenableFuture<org.springframework.kafka.support.SendResult<String, String>> future = this.kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Message [{}] delivered with offset {}",
                message,
                result.getRecordMetadata().offset());
            }
            @Override
            public void onFailure(Throwable ex) {
                logger.warn("Unable to deliver message [{}]. {}", 
                message,
                ex.getMessage());
            }
          });
    }
}
