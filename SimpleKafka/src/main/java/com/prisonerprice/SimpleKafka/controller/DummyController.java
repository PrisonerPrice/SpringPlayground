package com.prisonerprice.SimpleKafka.controller;

import com.prisonerprice.SimpleKafka.model.People;
import com.prisonerprice.SimpleKafka.service.PeopleMessageService;
import com.prisonerprice.SimpleKafka.service.SimpleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DummyController {

    @Autowired
    private SimpleMessageService simpleMessageService;

    @Autowired
    private PeopleMessageService peopleMessageService;

    @GetMapping
    public String doSomething() {
        simpleMessageService.sendMessage("Now time is: " + new Date(System.currentTimeMillis()));
        People people = new People();
        people.setFirstName("Stan");
        people.setLastName("Smith");
        people.setId("asgdhashdgkaj");
        people.setSsn("2345678");
        peopleMessageService.sendMessage(people);
        return "Send a message!";
    }
}
