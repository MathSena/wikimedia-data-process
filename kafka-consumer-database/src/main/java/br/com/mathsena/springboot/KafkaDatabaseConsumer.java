package br.com.mathsena.springboot;

import br.com.mathsena.springboot.entity.WikimediaData;
import br.com.mathsena.springboot.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @Value("${kafka.consumer.topic.name}")
    private String topicName;

    @Value("${kafka.consumer.group.id}")
    private String groupId;

    @Autowired
    private WikimediaDataRepository dataRepository;

    @KafkaListener(topics = "#{__listener.topicName}", groupId = "#{__listener.groupId}")
    public void consume(String recentChangeMessage){
        if (recentChangeMessage == null || recentChangeMessage.isEmpty()) {
            LOGGER.warn("Received null or empty message from kafka");
            return;
        }

        try {
            LOGGER.info(String.format("Event message received %s", recentChangeMessage));

            WikimediaData wikimediaData = new WikimediaData();
            wikimediaData.setWikiEventData(recentChangeMessage);

            dataRepository.save(wikimediaData);

        } catch (Exception e) {
            LOGGER.error("Error processing message: " + recentChangeMessage, e);
        }
    }
}
