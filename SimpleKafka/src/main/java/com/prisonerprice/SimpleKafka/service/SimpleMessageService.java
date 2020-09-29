package com.prisonerprice.SimpleKafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class SimpleMessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String SIMPLE_TOPIC = "simpleMessage";

    public void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(SIMPLE_TOPIC, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

    public void sendMessageWithoutCallback(String message) {
        kafkaTemplate.send(SIMPLE_TOPIC, message);
    }

    @KafkaListener(topics = SIMPLE_TOPIC, groupId = "simple_group")
    public void simpleListener(String message) {
        System.out.println("Received simple string: " + message);
    }
}
