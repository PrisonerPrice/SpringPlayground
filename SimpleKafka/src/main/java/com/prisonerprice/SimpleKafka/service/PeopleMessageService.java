package com.prisonerprice.SimpleKafka.service;

import com.prisonerprice.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class PeopleMessageService {

    @Autowired
    private KafkaTemplate<String, People> peopleKafkaTemplate;

    private final String PEOPLE_TOPIC = "peopleMessage";

    public void sendMessage(People people) {

        ListenableFuture<SendResult<String, People>> future =
                peopleKafkaTemplate.send(PEOPLE_TOPIC, people);

        future.addCallback(new ListenableFutureCallback<SendResult<String, People>>() {

            @Override
            public void onSuccess(SendResult<String, People> result) {
                System.out.println("Sent message=[" + people.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + people.toString() + "] due to : " + ex.getMessage());
            }
        });
    }

    public void sendMessageWithoutCallback(People people) {
        peopleKafkaTemplate.send(PEOPLE_TOPIC, people);
    }

    @KafkaListener(topics = PEOPLE_TOPIC, groupId = "people_group", containerFactory = "peopleListenerContainerFactory")
    public void peopleListener(People people) {
        System.out.println("Received people message: " + people.toString());
    }
}
