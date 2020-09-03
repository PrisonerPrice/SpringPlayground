package com.prisonerprice.reactiveMongoTesting.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
@PropertySource("classpath:application.properties")
@Profile("dev")
public class ProdConfig extends AbstractReactiveMongoConfiguration {
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create("mongodb://" + host + ":" + port);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
