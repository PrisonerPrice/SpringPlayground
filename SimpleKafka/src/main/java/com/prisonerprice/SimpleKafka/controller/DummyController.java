package com.prisonerprice.SimpleKafka.controller;

import com.prisonerprice.SimpleKafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DummyController {

    @Autowired
    private KafkaService kafkaService;

    @GetMapping
    public String doSomething() {
        kafkaService.sendMessage("Now time is: " + new Date(System.currentTimeMillis()));
        return "Send a message!";
    }
}
