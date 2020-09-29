package com.prisonerprice.SimpleKafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic simpleTopic() {
        return new NewTopic("simpleMessage", 1, (short) 1);
    }

    @Bean
    public NewTopic peopleTopic() {
        return new NewTopic("peopleMessage", 1, (short) 1);
    }
}
