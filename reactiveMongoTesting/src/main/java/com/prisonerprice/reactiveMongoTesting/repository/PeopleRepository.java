package com.prisonerprice.reactiveMongoTesting.repository;

import com.prisonerprice.reactiveMongoTesting.model.People;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends ReactiveMongoRepository<People, String> {
}
