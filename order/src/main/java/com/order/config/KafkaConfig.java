package com.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${topic-name}")
    String TOPIC_NAME;
    @Value("${topic-partitions}")
    int TOPIC_PARTITIONS;
    @Value("${topic-replicas}")
    int TOPIC_REPLICAS;
    @Bean
    public NewTopic createTopic() {
        return new NewTopic(TOPIC_NAME, TOPIC_PARTITIONS, (short)TOPIC_REPLICAS);
    }
}
