package com.prisonerprice.reactiveMongoTesting.service;

import com.prisonerprice.reactiveMongoTesting.model.People;
import com.prisonerprice.reactiveMongoTesting.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;
    
    public List<People> getAllPeople() {
        return peopleRepository.findAll().toStream().collect(Collectors.toList());
    }
    
    public People savePeople(People people) {
        return peopleRepository.save(people).block();
    }
    
    public void deleteAllPeople() {
        peopleRepository.deleteAll().subscribe();
    }
}
