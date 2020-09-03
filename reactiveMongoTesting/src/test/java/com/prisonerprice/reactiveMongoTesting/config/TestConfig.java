package com.prisonerprice.reactiveMongoTesting.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@TestConfiguration
@EnableMongoRepositories
@PropertySource("classpath:application.properties")
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
@Profile("test")
public class TestConfig extends AbstractReactiveMongoConfiguration {
    @Value("${spring.data.mongodb.database}")
    private String database;
    
    @Value("${spring.data.mongodb.host}")
    private String host;
    
    @Value("${spring.data.mongodb.port}")
    private String port;
    
    @Bean
    @DependsOn("embeddedMongoServer")
    public MongoClient testReactiveMongoClient() {
        return MongoClients.create("mongodb://" + host + ":" + port);
    }
    
    @Override
    protected String getDatabaseName() {
        return database;
    }
}
