package br.com.mathsena.springboot;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.name}")
    private String topicName;

    /**
     * Bean creation for the Kafka topic.
     * A topic is created in the Kafka server.
     * @return NewTopic A new instance of a NewTopic.
     */
    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topicName)
                .build();
    }
}
