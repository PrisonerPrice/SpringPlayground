package com.prisonerprice.reactiveMongoTesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoReactiveAutoConfiguration.class})
public class ReactiveMongoTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoTestingApplication.class, args);
	}

}
