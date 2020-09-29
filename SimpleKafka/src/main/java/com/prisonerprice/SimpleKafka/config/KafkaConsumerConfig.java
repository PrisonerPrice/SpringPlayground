package com.prisonerprice.SimpleKafka.config;

import com.prisonerprice.SimpleKafka.avro.AvroDeserializer;
import com.prisonerprice.SimpleKafka.model.People;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    private final static String GROUP_ID = "dummy";

    /*
     * Simple String message
     */

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                GROUP_ID);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    /*
     * People message
     */

    @Bean
    public ConsumerFactory<String, People> consumerFactoryForPeople() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                GROUP_ID);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                AvroDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new AvroDeserializer<>(People.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, People>
    kafkaListenerContainerFactoryForPeople() {
        ConcurrentKafkaListenerContainerFactory<String, People> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryForPeople());
        return factory;
    }
}
