package br.com.mathsena.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    private static final String TOPIC_NAME = "wikimedia_recentChange";
    private static final String GROUP_ID = "myGroup";

    @KafkaListener(topics=TOPIC_NAME, groupId=GROUP_ID)
    public void consume(String recentChangeMessage){
        try {
            LOGGER.info(String.format("Event message received %s", recentChangeMessage));
            // Process the message here
        } catch (Exception e) {
            LOGGER.error("Error processing message: ", e);
        }
    }
}